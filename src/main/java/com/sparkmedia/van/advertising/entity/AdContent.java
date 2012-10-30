package com.sparkmedia.van.advertising.entity;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-17
 * Time: 下午2:59
 * AdContent represent the material of the advertising,
 * for example: a picture, a video clip or a flash file.
 */
public class AdContent {
    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String objToString() {
        return (x.toString() + "," + y.toString() + "," + w.toString() + "," + h.toString());
    }

    public AdContent stringToObj(String ObjInfo) {
        return this;
    }
}
