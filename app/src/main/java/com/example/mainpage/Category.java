package com.example.mainpage;

public class Category {
    private int categoryImg;
    private String categoryTitle;

    public Category(int categoryImg, String categoryTitle) {
        this.categoryImg = categoryImg;
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryImg() {
        return categoryImg;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }
}
