package my.maslianah.com.pickfeastfyp.model;

import java.text.DecimalFormat;
public class Packages {

    private String packID;
    private String packName;
    private String packCuisine;
    private Boolean isPackOnSpot;
    private Boolean isPackBigEvent;
    private String packDescription;
    private float packPrice;
    private String catID;
    private String packLocation;
    private String packAddress;
    private Boolean isPackHalal;
    private int count = 0;

    public int getCount() {
        return count++;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public Packages(String packName, String packCuisine, float packPrice, Boolean isPackOnSpot, Boolean isPackBigEvent, String packDescription, String catID, String packLocation, String packAddress, Boolean isPackHalal) {
        this.packName = packName;
        this.packCuisine = packCuisine;
        this.isPackOnSpot = isPackOnSpot;
        this.isPackBigEvent = isPackBigEvent;
        this.packDescription = packDescription;
        this.packPrice = packPrice;
        this.catID = catID;
        this.packLocation = packLocation;
        this.packAddress = packAddress;
        this.isPackHalal = isPackHalal;
    }


    public Packages (){}

    public String getPackLocation() {
        return packLocation;
    }

    public void setPackLocation(String packLocation) {
        this.packLocation = packLocation;
    }

    public String getPackAddress() {
        return packAddress;
    }

    public void setPackAddress(String packAddress) {
        this.packAddress = packAddress;
    }

    public Boolean getPackHalal() {
        return isPackHalal;
    }

    public void setPackHalal(Boolean packHalal) {
        isPackHalal = packHalal;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    public String getPackName() {
        return  this.packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getPackCuisine() {
        return  this.packCuisine;
    }

    public void setPackCuisine(String packCuisine) {
        this.packCuisine = packCuisine;
    }

    public Boolean getPackOnSpot() {
        return  this.isPackOnSpot;
    }

    public void setPackOnSpot(Boolean packOnSpot) {
        isPackOnSpot = packOnSpot;
    }

    public Boolean getPackBigEvent() {
        return  this.isPackBigEvent;
    }

    public void setPackBigEvent(Boolean packBigEvent) {
        isPackBigEvent = packBigEvent;
    }

    public String getPackDescription() {
        return  this.packDescription;
    }

    public void setPackDescription(String packDescription) {
        this.packDescription = packDescription;
    }

    public float getPackPrice() {
        return this.packPrice;
    }

    public void setPackPrice(float packPrice) {

        this.packPrice = packPrice;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String toStringFirst() {
        DecimalFormat priceFormatter = new DecimalFormat("#0.00");

        return "\n" + packName  +
                "\nPrice: RM " + priceFormatter.format(packPrice) +
                "\n" + packLocation;
    }

    public String toStringSecond() {
        DecimalFormat priceFormatter = new DecimalFormat("#0.00");
        String s,b;
        if(isPackOnSpot){
            s = "\n\nWe cater for On Spot occasions";
        }
        else{

            s = "\n ";
        }
        if(isPackBigEvent){
            b = "\nWe cater for Big Events";
        }
        else {
            b = "\n ";
        }

        return  "\n" + packName  +
                "\nPrice of the package is RM " + priceFormatter.format(packPrice) + "\n" +
                "\nThe cuisine is " + packCuisine  +
                s + b +
                "\n" + packDescription +
            //    "\n" + packAddress +
                "\n";
    }
}
