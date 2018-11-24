package com.example.shoppingcart.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String category_id;

    @Column(name = "category_name")
    private String category_name;

    @ManyToOne
    @JoinColumn(name = "username")
    private User owner;

    public Category(String category_name, User owner) {
        this.category_name = category_name;
        this.owner = owner;
    }

    public Category() {}

    public String getId() {
        return category_id;
    }

    public void setId(String id) {
        this.category_id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}