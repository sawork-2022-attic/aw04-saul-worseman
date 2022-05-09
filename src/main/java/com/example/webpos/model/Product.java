package com.example.webpos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "products")
public class Product {

    @Id
    private String asin;

    private String main_cat;

    private String title;


    @ElementCollection
    private List<String> category;

    @ElementCollection
    private List<String> imageURLHighRes;

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getMain_cat() {
        return main_cat;
    }

    public void setMain_cat(String main_cat) {
        this.main_cat = main_cat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getImageURLHighRes() {
        return imageURLHighRes;
    }

    public void setImageURLHighRes(List<String> imageURLHighRes) {
        this.imageURLHighRes = imageURLHighRes;
    }

}
