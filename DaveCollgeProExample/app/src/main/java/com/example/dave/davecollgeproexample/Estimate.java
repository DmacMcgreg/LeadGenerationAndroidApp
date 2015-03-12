package com.example.dave.davecollgeproexample;

/**
 * Created by Dave on 3/12/2015.
 */
public class Estimate {

    // Declare Hours for each type
    private float windowHours, eavesHours, sidingHours, screenHours,
            oneSideHours, twoSideHours = 0;

    //Declare Prices
    private float windowsPrice, eavesPrice, sidingPrice, screensPrice, inflateOne,
            discountOne, inflateTwo, discountTwo = 0;



    private float windows1Side, ftEaves, ftSiding, screens = 0;

    public Estimate() {

    }

    public void setHours(float windowHours, float screenHours, float eavesHours, float sidingHours) {
        setWindowHours(windowHours);
        setScreenHours(screenHours);
        setEavesHours(eavesHours);
        setSidingHours(sidingHours);
    }

    public void setCounts(float windows1Side, float screens, float ftEaves, float ftSiding) {
        setWindows1Side(windows1Side);
        setScreens(screens);
        setFtEaves(ftEaves);
        setFtSiding(ftSiding);
    }

    public void setPrices(float inflateOne, float discountOne, float eavesPrice, float sidingPrice) {
        setInflateOne(inflateOne);
        setDiscountOne(discountOne);
        setEavesPrice(eavesPrice);
        setSidingPrice(sidingPrice);
    }

    public float getWindowHours() {
        return windowHours;
    }

    public void setWindowHours(float windowHours) {
        this.windowHours = windowHours;
    }

    public float getEavesHours() {
        return eavesHours;
    }

    public void setEavesHours(float eavesHours) {
        this.eavesHours = eavesHours;
    }

    public float getSidingHours() {
        return sidingHours;
    }

    public void setSidingHours(float sidingHours) {
        this.sidingHours = sidingHours;
    }

    public float getScreenHours() {
        return screenHours;
    }

    public void setScreenHours(float screenHours) {
        this.screenHours = screenHours;
    }

    public float getOneSideHours() {
        return oneSideHours;
    }

    public void setOneSideHours(float oneSideHours) {
        this.oneSideHours = oneSideHours;
    }

    public float getTwoSideHours() {
        return twoSideHours;
    }

    public void setTwoSideHours(float twoSideHours) {
        this.twoSideHours = twoSideHours;
    }

    public float getWindowsPrice() {
        return windowsPrice;
    }

    public void setWindowsPrice(float windowsPrice) {
        this.windowsPrice = windowsPrice;
    }

    public float getEavesPrice() {
        return eavesPrice;
    }

    public void setEavesPrice(float eavesPrice) {
        this.eavesPrice = eavesPrice;
    }

    public float getSidingPrice() {
        return sidingPrice;
    }

    public void setSidingPrice(float sidingPrice) {
        this.sidingPrice = sidingPrice;
    }

    public float getScreensPrice() {
        return screensPrice;
    }

    public void setScreensPrice(float screensPrice) {
        this.screensPrice = screensPrice;
    }

    public float getInflateOne() {
        return inflateOne;
    }

    public void setInflateOne(float inflateOne) {
        this.inflateOne = inflateOne;
    }

    public float getDiscountOne() {
        return discountOne;
    }

    public void setDiscountOne(float discountOne) {
        this.discountOne = discountOne;
    }

    public float getInflateTwo() {
        return inflateTwo;
    }

    public void setInflateTwo(float inflateTwo) {
        this.inflateTwo = inflateTwo;
    }

    public float getDiscountTwo() {
        return discountTwo;
    }

    public void setDiscountTwo(float discountTwo) {
        this.discountTwo = discountTwo;
    }

    public float getWindows1Side() {
        return windows1Side;
    }

    public void setWindows1Side(float windows1Side) {
        this.windows1Side = windows1Side;
    }

    public float getFtEaves() {
        return ftEaves;
    }

    public void setFtEaves(float ftEaves) {
        this.ftEaves = ftEaves;
    }

    public float getFtSiding() {
        return ftSiding;
    }

    public void setFtSiding(float ftSiding) {
        this.ftSiding = ftSiding;
    }

    public float getScreens() {
        return screens;
    }

    public void setScreens(float screens) {
        this.screens = screens;
    }


}
