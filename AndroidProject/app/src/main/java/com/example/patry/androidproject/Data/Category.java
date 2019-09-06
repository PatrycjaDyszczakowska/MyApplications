package com.example.patry.androidproject.Data;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<CategoryItem> categoryItems = new ArrayList<>();

    public Category(String name){
        this.name = name;
    }

    public void setCategoryItems(String name, Double price, String info_short, String info_long, Integer image_id, Boolean favorite,
                                 Double screen, String operating_system){
        categoryItems.add(new CategoryItem(name, price, info_short, info_long, image_id, favorite, screen, operating_system));
    }

    public ArrayList<CategoryItem> getCategoryItems() {
        return categoryItems;
    }
}
