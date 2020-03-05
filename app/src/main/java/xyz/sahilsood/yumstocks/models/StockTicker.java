package xyz.sahilsood.yumstocks.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StockTicker implements Serializable {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("price")
    public double price;

    @SerializedName("companyType")
    public List<String> companyType;

    public StockTicker(String id, String name, double price, List<String> companyType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.companyType = companyType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getCompanyType() {
        return companyType;
    }

    public void setCompanyType(List<String> companyType) {
        this.companyType = companyType;
    }

    public StockTicker() {
    }

    @Override
    public String toString() {
        return "StockTicker{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", companyType=" + companyType +
                '}';
    }
}