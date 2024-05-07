package com.ionut.sneakerdata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EbayModel {

    @SerializedName("warning")
    @Expose
    private Object warning;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("average_price")
    @Expose
    private Double averagePrice;
    @SerializedName("median_price")
    @Expose
    private Double medianPrice;
    @SerializedName("min_price")
    @Expose
    private Double minPrice;
    @SerializedName("max_price")
    @Expose
    private Double maxPrice;
    @SerializedName("results")
    @Expose
    private Integer results;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("pages_included")
    @Expose
    private Integer pagesIncluded;
    @SerializedName("products")
    @Expose
    private List<Product> products;

    public Object getWarning() {
        return warning;
    }

    public void setWarning(Object warning) {
        this.warning = warning;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Double getMedianPrice() {
        return medianPrice;
    }

    public void setMedianPrice(Double medianPrice) {
        this.medianPrice = medianPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getPagesIncluded() {
        return pagesIncluded;
    }

    public void setPagesIncluded(Integer pagesIncluded) {
        this.pagesIncluded = pagesIncluded;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}

