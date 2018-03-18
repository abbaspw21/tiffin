package tif.eurekalabs.com.model;

import android.graphics.Bitmap;

/**
 * Created by pc on 3/13/2018.
 */

public class PackageItem {

    private String name;
    private String price;
    private String decription;
    private Bitmap image;

    public PackageItem(String name, String price, String decription, Bitmap image) {
        this.name = name;
        this.price = price;
        this.decription = decription;
        this.image = image;
    }

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

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
