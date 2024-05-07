package com.ionut.sneakerdata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SneakerModel {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("results")
    @Expose
    private List<Result> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("colorway")
        @Expose
        private String colorway;
        @SerializedName("estimatedMarketValue")
        @Expose
        private Integer estimatedMarketValue;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("image")
        @Expose
        private Image image;
        @SerializedName("links")
        @Expose
        private Links links;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("releaseDate")
        @Expose
        private String releaseDate;
        @SerializedName("releaseYear")
        @Expose
        private String releaseYear;
        @SerializedName("retailPrice")
        @Expose
        private Integer retailPrice;
        @SerializedName("silhouette")
        @Expose
        private String silhouette;
        @SerializedName("sku")
        @Expose
        private String sku;
        @SerializedName("story")
        @Expose
        private String story;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getColorway() {
            return colorway;
        }

        public void setColorway(String colorway) {
            this.colorway = colorway;
        }

        public Integer getEstimatedMarketValue() {
            return estimatedMarketValue;
        }

        public void setEstimatedMarketValue(Integer estimatedMarketValue) {
            this.estimatedMarketValue = estimatedMarketValue;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getReleaseYear() {
            return releaseYear;
        }

        public void setReleaseYear(String releaseYear) {
            this.releaseYear = releaseYear;
        }

        public Integer getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(Integer retailPrice) {
            this.retailPrice = retailPrice;
        }

        public String getSilhouette() {
            return silhouette;
        }

        public void setSilhouette(String silhouette) {
            this.silhouette = silhouette;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }


        public static class Image {

            @SerializedName("360")
            @Expose
            private List<Object> _360;
            @SerializedName("original")
            @Expose
            private String original;
            @SerializedName("small")
            @Expose
            private String small;
            @SerializedName("thumbnail")
            @Expose
            private String thumbnail;

            public List<Object> get360() {
                return _360;
            }

            public void set360(List<Object> _360) {
                this._360 = _360;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }


        }

        public class Links {

            @SerializedName("stockX")
            @Expose
            private String stockX;
            @SerializedName("goat")
            @Expose
            private String goat;
            @SerializedName("flightClub")
            @Expose
            private String flightClub;
            @SerializedName("stadiumGoods")
            @Expose
            private String stadiumGoods;

            public String getStockX() {
                return stockX;
            }

            public void setStockX(String stockX) {
                this.stockX = stockX;
            }

            public String getGoat() {
                return goat;
            }

            public void setGoat(String goat) {
                this.goat = goat;
            }

            public String getFlightClub() {
                return flightClub;
            }

            public void setFlightClub(String flightClub) {
                this.flightClub = flightClub;
            }

            public String getStadiumGoods() {
                return stadiumGoods;
            }

            public void setStadiumGoods(String stadiumGoods) {
                this.stadiumGoods = stadiumGoods;
            }

        }

    }

}