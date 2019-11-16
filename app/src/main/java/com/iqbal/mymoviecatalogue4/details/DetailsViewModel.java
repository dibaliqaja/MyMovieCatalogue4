package com.iqbal.mymoviecatalogue4.details;

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

import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Details>> dataD = new MutableLiveData<>();
    public void setDetails(Context context, boolean b, int id) {
        final ArrayList<Details> details = new ArrayList<>();
        if (b) {
            String linkm = "https://api.themoviedb.org/3/movie/" + id + "?api_key=14ce33fe830d64779347edb08303bb8b&language=en-US";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, linkm, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Details de = new Details(response, true);
                    details.add(de);
                    dataD.postValue(details);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onFailure", error.getMessage());
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(jsonObjectRequest);
        } else {
            String linktv = "https://api.themoviedb.org/3/tv/" + id + "?api_key=14ce33fe830d64779347edb08303bb8b&language=en-US";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, linktv, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Details de = new Details(response, false);
                    details.add(de);
                    dataD.postValue(details);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onFailure", error.getMessage());
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(jsonObjectRequest);
        }
    }
    LiveData<ArrayList<Details>> getDetails() {
        return dataD;
    }
}
