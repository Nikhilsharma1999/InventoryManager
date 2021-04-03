package com.example.InventoryManager.narola.Model;

import com.example.InventoryManager.narola.entity.Role;
import java.util.Set;

public class UserSignUpRequestModel {
    private int enabled;
    private String password;
    private String username;
    private Set<Role> roles;

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
