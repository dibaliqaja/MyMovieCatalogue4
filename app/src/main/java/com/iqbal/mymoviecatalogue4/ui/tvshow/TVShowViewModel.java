package com.iqbal.mymoviecatalogue4.ui.tvshow;

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
import com.iqbal.mymoviecatalogue4.model.TVShow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TVShowViewModel extends ViewModel {
    String link = "https://api.themoviedb.org/3/discover/tv?api_key=14ce33fe830d64779347edb08303bb8b&language=en-US";
    private MutableLiveData<ArrayList<TVShow>> listTV = new MutableLiveData<>();

    void setTVShow(Context context) {
        final ArrayList<TVShow> list = new ArrayList<>();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        TVShow tvShow1 = new TVShow(jsonObject);
                        list.add(tvShow1);
                    }
                    Log.d("datalist", String.valueOf(list));
                    listTV.postValue(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onFailure", "Failed");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
    }
    LiveData<ArrayList<TVShow>> getTVShow() {
        return listTV;
    }
}
