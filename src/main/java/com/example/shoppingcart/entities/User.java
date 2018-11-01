package com.example.shoppingcart.entities;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;


    @Column(name = "password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "role")
    private String role;


    public User() {}

    public String getId() {
        return username;
    }

    public void setId(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}