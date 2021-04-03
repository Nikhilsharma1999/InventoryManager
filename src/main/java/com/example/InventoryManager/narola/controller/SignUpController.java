package com.example.InventoryManager.narola.controller;

import com.example.InventoryManager.narola.Model.UserSignUpRequestModel;
import com.example.InventoryManager.narola.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public void addUser(@RequestBody UserSignUpRequestModel requestModel){
        signUpService.addUser(requestModel);
    }
}