package gukbab1216.com.chalkak.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Drama implements Serializable {

    private String compareTitle;
    private String description, image;
    private ArrayList<Picture> pictures;
    private String subImage;
    private String title;

    public Drama() {
    }

    public Drama(String compareTitle, String description, String image, ArrayList<Picture> pictures, String subImage, String title) {
        this.compareTitle = compareTitle;
        this.description = description;
        this.image = image;
        this.pictures = pictures;
        this.subImage = subImage;
        this.title = title;
    }

    public String getSubImage() {
        return subImage;
    }

    public void setSubImage(String subImage) {
        this.subImage = subImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    public String getCompareTitle() {
        return compareTitle;
    }

    public void setCompareTitle(String compareTitle) {
        this.compareTitle = compareTitle;
    }
}
