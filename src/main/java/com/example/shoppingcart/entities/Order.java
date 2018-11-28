package com.example.shoppingcart.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
/**
 * Entity class correspondint to the Order table in the db
 */
public class Order {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String order_id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User owner;



    @OneToMany
//    @JoinColumn(name="item_id")
    private List<Item> items = new ArrayList<>();

    @Column(name = "total_price")
    private Float total_price;

    @Column(name="order_status")
    private String status;

    public Order(User owner, List<Item> items, String status) {
        this.owner = owner;
        this.items = items;
        this.total_price = this.getTotalPrice();
        this.status = status;
    }

    public Order(){}

    public String getId() {
        return order_id;
    }

    public void setId(String id) {
        this.order_id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getTotalPrice (){
        Float total = Float.valueOf(0);

        for (Item item : this.items) {
            total += item.getPrice();
        }
        return total;
    }
}
