package com.example.shoppingcart.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "items")
/**
 * Entity class correspondint to the Item tablle in the db
 */
public class Item {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String item_id;

    @Column(name = "item_name")
    private String item_name;

    @Column(name ="price")
    private Float price;


    @ManyToOne
    @JoinColumn(name = "username")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category item_category;
    public Item() {}

    public Item(String item_name, Float price, User owner, Category item_category) {
        this.item_name = item_name;
        this.price = price;
        this.owner = owner;
        this.item_category = item_category;
    }

    public String getId() {
        return item_id;
    }

    public void setId(String id) {
        this.item_id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getItem_category() {
        return item_category;
    }

    public void setItem_category(Category item_category) {
        this.item_category = item_category;
    }
}