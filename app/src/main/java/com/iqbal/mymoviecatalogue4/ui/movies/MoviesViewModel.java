package com.iqbal.mymoviecatalogue4.ui.movies;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.iqbal.mymoviecatalogue4.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoviesViewModel extends ViewModel {
    String link = "https://api.themoviedb.org/3/discover/movie?api_key=14ce33fe830d64779347edb08303bb8b&language=en-US";
    private MutableLiveData<ArrayList<Movies>> listM = new MutableLiveData<>();

    void setMovie(Context context) {
        final ArrayList<Movies> list = new ArrayList<>();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        Movies movies1 = new Movies(jsonObject1);
                        list.add(movies1);
                    }
                    listM.postValue(list);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onFailure", error.getMessage());
            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(objectRequest);
    }
    LiveData<ArrayList<Movies>> getMovies() {
        return listM;
    }
}
