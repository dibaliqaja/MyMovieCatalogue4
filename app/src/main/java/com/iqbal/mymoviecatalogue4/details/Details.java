package com.iqbal.mymoviecatalogue4.details;

import org.json.JSONObject;

public class Details {
    private int id;
    private String title;
    private String image;
    private Double rating;
    private Double popularity;
    private String overview;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Details(JSONObject jsonObject, boolean b) {
        if (b) {
            try {
                int id = jsonObject.getInt("id");
                String title = jsonObject.getString("title");
                Double rating = jsonObject.getDouble("vote_average");
                Double popularity = jsonObject.getDouble("popularity");
                String overview = jsonObject.getString("overview");
                String image = "https://image.tmdb.org/t/p/w154" + jsonObject.getString("poster_path");

                this.id = id;
                this.title = title;
                this.rating = rating;
                this.popularity = popularity;
                this.overview = overview;
                this.image = image;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                int id = jsonObject.getInt("id");
                String title = jsonObject.getString("name");
                Double rating = jsonObject.getDouble("vote_average");
                Double popularity = jsonObject.getDouble("popularity");
                String overview = jsonObject.getString("overview");
                String image = "https://image.tmdb.org/t/p/w154" + jsonObject.getString("poster_path");

                this.id = id;
                this.title = title;
                this.rating = rating;
                this.popularity = popularity;
                this.overview = overview;
                this.image = image;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
