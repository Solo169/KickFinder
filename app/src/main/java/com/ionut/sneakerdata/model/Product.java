package com.ionut.sneakerdata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sale_price")
    @Expose
    private Double salePrice;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("buying_format")
    @Expose
    private String buyingFormat;
    @SerializedName("date_sold")
    @Expose
    private String dateSold;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("shipping_price")
    @Expose
    private Object shippingPrice;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("item_id")
    @Expose
    private String itemId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBuyingFormat() {
        return buyingFormat;
    }

    public void setBuyingFormat(String buyingFormat) {
        this.buyingFormat = buyingFormat;
    }

    public String getDateSold() {
        return dateSold;
    }

    public void setDateSold(String dateSold) {
        this.dateSold = dateSold;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Object shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

}
