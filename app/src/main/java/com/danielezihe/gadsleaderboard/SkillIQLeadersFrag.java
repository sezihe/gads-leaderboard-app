package com.danielezihe.gadsleaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


    private RecyclerAdapter mRecyclerAdapter;
    private ProgressBar mProgressBar;
    private ArrayList<ItemsHelper> mTopSkillIQ = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_skill_iq_leaders_frag, container, false);

        // initialize views
        mRecyclerView = v.findViewById(R.id.recyclerView_silf);
        mProgressBar = v.findViewById(R.id.progressBar_silf);

        // set up recyclerview
        setUpRecyclerView();

        return v;
    }

    private void getTopSkillIqFromAPI() {
        // make a get request to the server, requesting for a jsonArray
        JsonArrayRequest jsonObjectRequestSIQ = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // loop through all values in the received jsonArray
                    for (int i = 0; i < response.length(); i++) {
                        // get objects from jsonArray
                        JSONObject dataObj = response.getJSONObject(i);

                        // get values
                        String name = dataObj.getString("name");
                        String score = dataObj.getString("score");
                        String country = dataObj.getString("country");
                        String badgeUrl = dataObj.getString("badgeUrl");

                        // add a new Item with the values
                        mTopSkillIQ.add(new ItemsHelper(name, score, country, badgeUrl, true));
                    }
                    // ArrayList has been populated, loading is done. Remove progressBar
                    mProgressBar.setVisibility(View.GONE);

                    // notify adapter that data has been added to the ArrayList
                    mRecyclerAdapter.notifyDataSetChanged();
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
    }

    private void setUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);

        // initialize linear layout manager passing getContext() as the param
        mLinearLayoutManager = new LinearLayoutManager(getContext());

        // initialize custom recycler Adapter
        mRecyclerAdapter = new RecyclerAdapter(getContext(), mTopSkillIQ);

        // assign my layout manager to recyclerView
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // assign custom recycler adapter to recycler view
        mRecyclerView.setAdapter(mRecyclerAdapter);

        // populate ArrayList with values from gads-api.
        getTopSkillIqFromAPI();
    }
}
