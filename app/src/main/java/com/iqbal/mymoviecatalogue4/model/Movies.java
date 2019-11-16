package com.iqbal.mymoviecatalogue4.model;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Movies {
    private Integer id;
    private String title;
    private String image;
    private String date;
    private String overview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Movies(JSONObject jsonObject) {
        try {
            Integer id = jsonObject.getInt("id");
            String title = jsonObject.getString("title");
            String image = jsonObject.getString("poster_path");
            String date = jsonObject.getString("release_date");
            String overview = jsonObject.getString("overview");

            this.id = id;
            this.title = title;
            this.image = image;
            this.date = date;
            this.overview = overview;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
