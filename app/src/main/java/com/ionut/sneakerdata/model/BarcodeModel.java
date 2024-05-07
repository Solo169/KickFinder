package com.ionut.sneakerdata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BarcodeModel {

    @SerializedName("product")
    @Expose
    private Product product;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public static class Product {


        @SerializedName("artist")
        @Expose
        private Object artist;
        @SerializedName("attributes")
        @Expose
        private Attributes attributes;
        @SerializedName("author")
        @Expose
        private Object author;
        @SerializedName("barcode_formats")
        @Expose
        private BarcodeFormats barcodeFormats;
        @SerializedName("brand")
        @Expose
        private Object brand;
        @SerializedName("category")
        @Expose
        private List<String> category;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("features")
        @Expose
        private List<Object> features;
        @SerializedName("images")
        @Expose
        private List<String> images;
        @SerializedName("ingredients")
        @Expose
        private Object ingredients;
        @SerializedName("nutrition_facts")
        @Expose
        private Object nutritionFacts;
        @SerializedName("manufacturer")
        @Expose
        private String manufacturer;
        @SerializedName("online_stores")
        @Expose
        private List<OnlineStore> onlineStores;
        @SerializedName("title")
        @Expose
        private String title;

        public Object getArtist() {
            return artist;
        }

        public void setArtist(Object artist) {
            this.artist = artist;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        public Object getAuthor() {
            return author;
        }

        public void setAuthor(Object author) {
            this.author = author;
        }

        public BarcodeFormats getBarcodeFormats() {
            return barcodeFormats;
        }

        public void setBarcodeFormats(BarcodeFormats barcodeFormats) {
            this.barcodeFormats = barcodeFormats;
        }

        public Object getBrand() {
            return brand;
        }

        public void setBrand(Object brand) {
            this.brand = brand;
        }

        public List<String> getCategory() {
            return category;
        }

        public void setCategory(List<String> category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Object> getFeatures() {
            return features;
        }

        public void setFeatures(List<Object> features) {
            this.features = features;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public Object getIngredients() {
            return ingredients;
        }

        public void setIngredients(Object ingredients) {
            this.ingredients = ingredients;
        }

        public Object getNutritionFacts() {
            return nutritionFacts;
        }

        public void setNutritionFacts(Object nutritionFacts) {
            this.nutritionFacts = nutritionFacts;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public List<OnlineStore> getOnlineStores() {
            return onlineStores;
        }

        public void setOnlineStores(List<OnlineStore> onlineStores) {
            this.onlineStores = onlineStores;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public class OnlineStore {

            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("price")
            @Expose
            private String price;
            @SerializedName("url")
            @Expose
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }


        public class BarcodeFormats {

            @SerializedName("upc_a")
            @Expose
            private String upcA;
            @SerializedName("ean_13")
            @Expose
            private String ean13;

            public String getUpcA() {
                return upcA;
            }

            public void setUpcA(String upcA) {
                this.upcA = upcA;
            }

            public String getEan13() {
                return ean13;
            }

            public void setEan13(String ean13) {
                this.ean13 = ean13;
            }

        }

        public class Attributes {

            @SerializedName("mpn")
            @Expose
            private String mpn;
            @SerializedName("model")
            @Expose
            private String model;
            @SerializedName("size")
            @Expose
            private String size;
            @SerializedName("color")
            @Expose
            private String color;
            @SerializedName("gender")
            @Expose
            private String gender;

            public String getMpn() {
                return mpn;
            }

            public void setMpn(String mpn) {
                this.mpn = mpn;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

        }
    }
}

