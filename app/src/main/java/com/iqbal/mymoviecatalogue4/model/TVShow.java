package com.iqbal.mymoviecatalogue4.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TVShow {
    private int id;
    private String name;
    private String first_air_date;
    private String overview;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public TVShow(JSONObject jsonObject) {
        try {
            Integer id = jsonObject.getInt("id");
            String name = jsonObject.getString("original_name");
            String date = jsonObject.getString("first_air_date");
            String overview = jsonObject.getString("overview");
            String photo = jsonObject.getString("poster_path");

            this.id = id;
            this.name = name;
            this.first_air_date = date;
            this.overview = overview;
            this.photo = photo;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
