package com.cs.news1.fragment.fm_adapter.PhotoAdater;

/**
 * Created by Administrator on 2016/10/13.
 */

public class Product {
    private String localPosition;
    private String productName;
    private int width;
    private int height;




    public Product(String localPosition, String productName, int width, int height) {
        this.localPosition = localPosition;
        this.productName = productName;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {

        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Product(String localPosition, String productName) {
        this.localPosition = localPosition;
        this.productName = productName;
    }

    public Product() {
    }

    public String getLocalPosition() {

        return localPosition;
    }

    public void setLocalPosition(String localPosition) {
        this.localPosition = localPosition;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

