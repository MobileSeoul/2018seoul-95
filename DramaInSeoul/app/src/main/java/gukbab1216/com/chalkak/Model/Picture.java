package gukbab1216.com.chalkak.Model;

import java.io.Serializable;

public class Picture implements Serializable {

    String imgUrl;
    float latitude;
    float longitude;
    String posTitle;
    String filterImg;
    String filterLine;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getPosTitle() {
        return posTitle;
    }

    public void setPosTitle(String posTitle) {
        this.posTitle = posTitle;
    }

    public String getFilterImg() {
        return filterImg;
    }

    public void setFilterImg(String filterImg) {
        this.filterImg = filterImg;
    }

    public String getFilterLine() {
        return filterLine;
    }

    public void setFilterLine(String filterLine) {
        this.filterLine = filterLine;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "imgUrl='" + imgUrl + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", posTitle='" + posTitle + '\'' +
                ", filterImg='" + filterImg + '\'' +
                ", filterLine='" + filterLine + '\'' +
                '}';
    }
}
