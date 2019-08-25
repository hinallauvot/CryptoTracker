package com.example.hinal.cryptotrackerapp;

import android.os.Parcelable;

import java.io.Serializable;

public class AllCardClass
{
    private String name,symbol,price,change_hr,change_24hr,change_7d,rank,image,avlSupply,maxSupply,mktCap;
    boolean fav_state;


    public AllCardClass(String name, String symbol, String price, String change_hr, String change_24hr, String change_7d, String rank, String image, String avlSupply, String maxSupply, String mktCap) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.change_hr = change_hr;
        this.change_24hr = change_24hr;
        this.change_7d = change_7d;
        this.rank = rank;
        this.image = image;
        this.avlSupply = avlSupply;
        this.maxSupply = maxSupply;
        this.mktCap = mktCap;
    }

    public AllCardClass() {
    }

    public String getName() {
        return name;
    }

    public boolean isFav_state() {
        return fav_state;
    }

    public void setFav_state(boolean fav_state) {
        this.fav_state = fav_state;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public String getChange_hr() {
        return change_hr;
    }

    public String getChange_24hr() {
        return change_24hr;
    }

    public String getChange_7d() {
        return change_7d;
    }

    public String getRank() {
        return rank;
    }

    public String getImage() {
        return image;
    }

    public String getAvlSupply() {
        return avlSupply;
    }

    public void setAvlSupply(String avlSupply) {
        this.avlSupply = avlSupply;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getMktCap() {
        return mktCap;
    }

    public void setMktCap(String mktCap) {
        this.mktCap = mktCap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setChange_hr(String change_hr) {
        this.change_hr = change_hr;
    }

    public void setChange_24hr(String change_24hr) {
        this.change_24hr = change_24hr;
    }

    public void setChange_7d(String change_7d) {
        this.change_7d = change_7d;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
