package com.danielezihe.gadsleaderboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter("leadersList")
    public static void setLeadersList(final RecyclerView recyclerView, final ArrayList<ItemsHelper> itemsHelpers) {
        LinearLayoutManager mLinearLayoutManager;
        recyclerView.setHasFixedSize(true);

        // initialize linear layout manager passing getContext() as the param
        mLinearLayoutManager = new LinearLayoutManager(recyclerView.getContext());

        // initialize custom recycler Adapter
        RecyclerAdapter mRecyclerAdapter = new RecyclerAdapter(recyclerView.getContext(), itemsHelpers);

        // assign my layout manager to recyclerView
        recyclerView.setLayoutManager(mLinearLayoutManager);

        // assign custom recycler adapter to recycler view
        recyclerView.setAdapter(mRecyclerAdapter);
    }

}
