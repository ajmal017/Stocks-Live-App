package xyz.sahilsood.yumstocks.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StockDetail implements Serializable {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("price")
    public double price;

    @SerializedName("companyType")
    public List<String> companyType;

    @SerializedName("allTimeHigh")
    public double allTimeHigh;

    @SerializedName("address")
    public String address;

    @SerializedName("imageUrl")
    public String imageUrl;

    @SerializedName("website")
    public String website;

    public StockDetail() {
    }

    public StockDetail(String website, String imageUrl, String address, double allTimeHigh, List<String> companyType, double price, String name, String id) {
        this.website = website;
        this.imageUrl = imageUrl;
        this.address = address;
        this.allTimeHigh = allTimeHigh;
        this.companyType = companyType;
        this.price = price;
        this.name = name;
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAllTimeHigh() {
        return allTimeHigh;
    }

    public void setAllTimeHigh(double allTimeHigh) {
        this.allTimeHigh = allTimeHigh;
    }

    public List<String> getCompanyType() {
        return companyType;
    }

    public void setCompanyType(List<String> companyType) {
        this.companyType = companyType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StockDetail{" +
                "website='" + website + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", address='" + address + '\'' +
                ", allTimeHigh=" + allTimeHigh +
                ", companyType=" + companyType +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
