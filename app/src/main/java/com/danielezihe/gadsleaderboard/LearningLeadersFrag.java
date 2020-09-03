package com.danielezihe.gadsleaderboard;

import android.os.Bundle;
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
import com.danielezihe.gadsleaderboard.databinding.ActivityLearningLeadersFragBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LearningLeadersFrag extends Fragment {

    public static final String URL = "https://gadsapi.herokuapp.com/api/hours";
    public static final String GET_GADS_TOP_LEARNERS_REQUEST = "GET_GADS_TOP_LEARNERS_REQUEST";

    private ArrayList<ItemsHelper> mTopLearners = new ArrayList<>();

    // Data-Binding layout generated class
    ActivityLearningLeadersFragBinding mLearningLeadersFragBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate view
        mLearningLeadersFragBinding = ActivityLearningLeadersFragBinding.inflate(inflater);

        // main method
        getAndPopulateTopLearnersFromAPI();

        return mLearningLeadersFragBinding.getRoot();
    }

    private void initTopLearnersArrayList() {
        // assign TopLearnersArrayList to Layout's TopLearnersArrayList
        mLearningLeadersFragBinding.setTopLearnersArrList(mTopLearners);


    }

    private void getAndPopulateTopLearnersFromAPI() {
        // make a get request to the server, requesting for a jsonArray
        JsonArrayRequest jsonObjectRequestLL = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // loop through all values in the received jsonArray
                    for (int i = 0; i < response.length(); i++) {
                        // get objects from jsonArray
                        JSONObject dataObj = response.getJSONObject(i);

                        // get values
                        String name = dataObj.getString("name");
                        String hours = dataObj.getString("hours");
                        String country = dataObj.getString("country");
                        String badgeUrl = dataObj.getString("badgeUrl");

                        // add a new Item with the values
                        mTopLearners.add(new ItemsHelper(getContext(), name, hours, country, badgeUrl, false));

                    }
                    // sort arrayList by the highest hours
                    Collections.sort(mTopLearners, new Comparator<ItemsHelper>() {
                        @Override
                        public int compare(ItemsHelper lhs, ItemsHelper rhs) {
                            return Integer.parseInt(rhs.getMhours_score()) - Integer.parseInt(lhs.getMhours_score());
                        }
                    });

                    // ArrayList has been populated, loading is done. Remove progressBar
                    mLearningLeadersFragBinding.progressBarLlf.setVisibility(View.GONE);

                    // init LearnersArrayList with populated ArrayList.
                    initTopLearnersArrayList();

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
        jsonObjectRequestLL.setRetryPolicy(new DefaultRetryPolicy());
        jsonObjectRequestLL.setTag(GET_GADS_TOP_LEARNERS_REQUEST);
        MySingleTon.getInstance(getContext()).addToRequestQue(jsonObjectRequestLL);
    }
}
