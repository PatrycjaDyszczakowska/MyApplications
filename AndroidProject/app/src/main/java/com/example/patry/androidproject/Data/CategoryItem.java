package com.example.patry.androidproject.Data;

public class CategoryItem {
    private String name;
    private Double price;
    private String info_short;
    private String info_long;
    private Integer image_id;
    private Boolean favorite;
    private Double screen;
    private String operating_system;

    CategoryItem(String name, Double price, String info_short, String info_long, Integer image_id, Boolean favorite,
                        Double screen, String operating_system) {
        this.name = name;
        this.price = price;
        this.info_short = info_short;
        this.info_long = info_long;
        this.image_id = image_id;
        this.favorite = favorite;
        this.screen = screen;
        this.operating_system = operating_system;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInfo_short() {
        return info_short;
    }

    public void setInfo_short(String info_short) {
        this.info_short = info_short;
    }

    public String getInfo_long() {
        return info_long;
    }

    public void setInfo_long(String info_long) {
        this.info_long = info_long;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Double getScreen() {
        return screen;
    }

    public void setScreen(Double screen) {
        this.screen = screen;
    }

    public String getOperating_system() {
        return operating_system;
    }

    public void setOperating_system(String operating_system) {
        this.operating_system = operating_system;
    }
}
