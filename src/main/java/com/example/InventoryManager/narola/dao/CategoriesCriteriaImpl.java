package com.example.InventoryManager.narola.dao;

import com.example.InventoryManager.narola.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoriesCriteriaImpl implements CategoriesCriteria {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Categories> searchAnyCategory(String value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Categories> cq = cb.createQuery(Categories.class);
        Root<Categories> categoriesRoot = cq.from(Categories.class);
        cq.select(categoriesRoot).where(cb.like(categoriesRoot.get("catogName"),"%"+value+"%"));
        TypedQuery<Categories> query = entityManager.createQuery(cq);

        return query.getResultList();
    }
}
