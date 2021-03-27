package com.example.InventoryManager.narola.dao;

import com.example.InventoryManager.narola.entity.Categories;
import com.example.InventoryManager.narola.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.ArrayUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductCriteriaImpl implements ProductCriteria {

    @Autowired
    EntityManager entityManager;
    @Override
    public List<Products> searchAnyProduct(String value) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> productsRoot = cq.from(Products.class);
        /*cq.select(productsRoot).where(cb.like(productsRoot.get("productName"),"%"+value+"%"));*/
        List<Predicate> list = new ArrayList<>();
        list.add(cb.like(productsRoot.get("productName"),"%"+value+"%"));
        list.add(cb.like(productsRoot.get("description"),"%"+value+"%"));
        Predicate finalPredicate = cb.or(list.toArray(new Predicate[list.size()]));

        cq.where(finalPredicate).orderBy(cb.desc(productsRoot.get("productId")));
        TypedQuery<Products> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public List<Products> FindProduct(String value, int catId, String sortOrder, String sortBy, Pageable page) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
        Root<Products> productsRoot = cq.from(Products.class);


        if(isEmpty(value,catId)){
            cq.select(productsRoot);
            if(sortOrder.equalsIgnoreCase("asc")){
                cq.where().orderBy(cb.asc(productsRoot));
            }else{
                cq.where().orderBy(cb.desc(productsRoot));
            }
        }
        else if(value.equals("")) {
            Predicate predicateById = cb.equal(productsRoot.get("category"),catId);
            if(sortOrder.equalsIgnoreCase("asc")){
                if(!sortBy.trim().equals("")) {
                    cq.where(predicateById).orderBy(cb.asc(productsRoot.get(sortBy)));
                }else{
                    cq.where(predicateById).orderBy(cb.asc(productsRoot));
                }
            }else{
                if(!sortBy.trim().equals("")) {
                    cq.where(predicateById).orderBy(cb.desc(productsRoot.get(sortBy)));
                }else {
                    cq.where(predicateById).orderBy(cb.desc(productsRoot));
                }
            }
        }
        else {
            List<Predicate> list = new ArrayList<>();
            list.add(cb.like(productsRoot.get("productName"), "%" + value + "%"));
            list.add(cb.like(productsRoot.get("description"), "%" + value + "%"));

            Predicate PredicateById = cb.equal(productsRoot.get("category"), catId);

            Predicate pre1 = cb.or(list.toArray(new Predicate[list.size()]));
            Predicate pre2 = cb.and(PredicateById);

            if (sortOrder.equalsIgnoreCase("asc")) {
                cq.where(pre1, pre2).orderBy(cb.asc(productsRoot.get(sortBy)));
            } else {
                cq.where(pre1, pre2).orderBy(cb.desc(productsRoot.get(sortBy)));
            }
        }
        TypedQuery<Products> query = entityManager.createQuery(cq);

        int totalRows = query.getResultList().size();
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        Page<Products> results = new PageImpl<Products>(query.getResultList(),page,totalRows);
        List<Products> resultList = results.getContent();
        return resultList;
    }

    private boolean isEmpty(String value, int catId){
        return value.trim().equals("") && catId==0;
    }

}
