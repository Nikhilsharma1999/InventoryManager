package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.narola.Model.ProductsModel;
import com.example.InventoryManager.narola.dao.CartRepository;
import com.example.InventoryManager.narola.dao.ProductsDao;
import com.example.InventoryManager.narola.dao.UserRepository;
import com.example.InventoryManager.narola.entity.Cart;
import com.example.InventoryManager.narola.entity.Products;
import com.example.InventoryManager.narola.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductsDao productsDao;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(Principal principal, int pId) {
        int cartId = userRepository.findByUsername(principal.getName()).getCart().getCartId();
        Cart cart = cartRepository.findById(cartId).get();
        Products product = productsDao.findById(pId).get();
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    /*@Override
    public Cart update(Cart cart) {
        return cartRepository.save(cart);
    }*/

    @Override
    public void delete(Principal principal,int pId) {
        int cId = userRepository.findByUsername(principal.getName()).getCart().getCartId();
        Cart cart = cartRepository.findById(cId).get();
        Products products = productsDao.findById(pId).get();
        cart.getProducts().remove(products);
        cartRepository.save(cart);
    }

    public Set<ProductsModel> getAllProductsByCartId(Principal principal) {
        int cartId = userRepository.findByUsername(principal.getName()).getCart().getCartId();
        Set<Products> product = cartRepository.findById(cartId).get().getProducts();
        return EntitiesToModel(product);
    }

    private Set<ProductsModel> EntitiesToModel(Set<Products> product) {
        return product.stream().map(myProducts ->{
            ProductsModel model = new ProductsModel();
            model.setProductId(myProducts.getProductId());
            model.setProductName(myProducts.getProductName());
            model.setDescription(myProducts.getDescription());
            model.setCatogId(myProducts.getCategory().getCatogId());
            model.setCatogName(myProducts.getCategory().getCatogName());
            return model;
        }).collect(Collectors.toSet());
    }
}