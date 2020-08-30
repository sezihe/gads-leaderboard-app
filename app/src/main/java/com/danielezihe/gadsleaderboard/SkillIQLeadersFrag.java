package com.danielezihe.gadsleaderboard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SkillIQLeadersFrag extends Fragment {

    public static final String URL = "https://gadsapi.herokuapp.com/api/skilliq";
    public static final String GET_GADS_TOP_SKILLIQ_REQUEST = "GET_GADS_TOP_SKILLIQ_REQUEST";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_skill_iq_leaders_frag, container, false);

        return v;
    }

    private ArrayList<ItemsHelper> getTopSkillIqFromAPI() {
        final ArrayList<ItemsHelper> topSkillIQ = new ArrayList<>();

        // make a get request to the server
        JsonArrayRequest jsonObjectRequestSIQ = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // loop through all values
                    for (int i = 0; i < response.length(); i++) {
                        // get objects in jsonArr
                        JSONObject dataObj = response.getJSONObject(i);

                        // get values
                        String name = dataObj.getString("name");
                        String score = dataObj.getString("score");
                        String country = dataObj.getString("country");
                        String badgeUrl = dataObj.getString("badgeUrl");

                        // add a new Item with the values
                        topSkillIQ.add(new ItemsHelper(name, score, country, badgeUrl));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.err.println(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.err.println(error.getMessage());
            }
        });

        // set retry policy, tag and add to singleton request que
        jsonObjectRequestSIQ.setRetryPolicy(new DefaultRetryPolicy());
        jsonObjectRequestSIQ.setTag(GET_GADS_TOP_SKILLIQ_REQUEST);
        MySingleTon.getInstance(getContext()).addToRequestQue(jsonObjectRequestSIQ);

        return topSkillIQ;
    }
}
