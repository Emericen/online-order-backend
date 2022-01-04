package com.usc.onlineOrder.entity;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 2455760938054036111L;

    @Id
    private int id;
    private String address;
    private String name;
    private String phone;
    private String imageUrl;


    // F.K. of restaurant is stored in all menu item
    // This way you can get restaurant.menuItemList from restaurant objects

    // w/o mappedBy, hibernate automatically creates third table, aka. joint table.
    // w/ mappedBy, tables are linked by F.K.

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<MenuItem> menuItemList;

    // Example of many-to-many relationship in hibernate. Checkout the Baeldung blog for more
    // Hibernate will create joint table for us so no need to do it manually
    // Create relationship ONCE, in ONE class, not in both.

    // @Entity
    // class Student {
    //     @Id
    //     long id;
    //
    //     @ManyToMany
    //     Set<Course> likedCourse;
    // }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }




    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

}
