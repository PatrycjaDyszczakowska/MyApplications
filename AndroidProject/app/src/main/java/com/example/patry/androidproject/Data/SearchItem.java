package com.example.patry.androidproject.Data;

public class SearchItem {
    private Integer category;
    private Integer position;
    private String name;
    private Double price;
    private String info_short;
    private String info_long;
    private Integer image_id;
    private Boolean favorite;
    private String operating_system;

    public SearchItem(Integer category, Integer position, String name, Double price, String info_short,
                      String info_long, Integer image_id, Boolean favorite, String operating_system) {
        this.category = category;
        this.position = position;
        this.name = name;
        this.price = price;
        this.info_short = info_short;
        this.info_long = info_long;
        this.image_id = image_id;
        this.favorite = favorite;
        this.operating_system = operating_system;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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

    public String getOperating_system() {
        return operating_system;
    }

    public void setOperating_system(String operating_system) {
        this.operating_system = operating_system;
    }
}
