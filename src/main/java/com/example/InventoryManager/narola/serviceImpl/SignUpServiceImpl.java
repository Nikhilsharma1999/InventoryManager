package com.example.InventoryManager.narola.serviceImpl;

import com.example.InventoryManager.narola.Model.UserSignUpRequestModel;
import com.example.InventoryManager.narola.dao.UserRepository;
import com.example.InventoryManager.narola.entity.Cart;
import com.example.InventoryManager.narola.entity.User;
import com.example.InventoryManager.narola.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserSignUpRequestModel requestModel) {
        User user = new User();
        user.setUsername(requestModel.getUsername());
        String passwordString = requestModel.getPassword();
        String generatedSecuredPasswordHash = BCrypt.hashpw(passwordString,BCrypt.gensalt(10));
        user.setPassword(generatedSecuredPasswordHash);
        user.setEnabled(requestModel.getEnabled());
        user.setRoles(requestModel.getRoles());

        Cart cart=new Cart();
        cart.setProducts(null);
        cart.setUser(user);

        user.setCart(cart);
        userRepository.save(user);
    }
}
