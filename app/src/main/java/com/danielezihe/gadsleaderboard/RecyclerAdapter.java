package com.danielezihe.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.danielezihe.gadsleaderboard.databinding.RecycleItemBinding;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RAVBindingHolder> {

    private Context mContext;
    private ArrayList<ItemsHelper> mItemsHelperArrayList;

    // create a constructor
    public RecyclerAdapter(Context context, ArrayList<ItemsHelper> itemsHelperArrayList) {
        mContext = context;
        mItemsHelperArrayList = itemsHelperArrayList;
    }

    @NonNull
    @Override
    public RAVBindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate layout with recycler item
        RecycleItemBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycle_item, parent, false);

        return new RAVBindingHolder(mBinding.getRoot());
    }

    // bind data from Items ArrayList to our views
    @Override
    public void onBindViewHolder(@NonNull RAVBindingHolder holder, int position) {
        ItemsHelper itemsHelper = mItemsHelperArrayList.get(position);

        // assign itemsHelper to Layout's itemsHelper
        holder.mRecycleItemBinding.setItemshelper(itemsHelper);

        // set default placeholders from local directory
        if(itemsHelper.getMisSkillIQ()) {
            holder.mRecycleItemBinding.setPlaceholder(R.drawable.skill_iq_trimmed);
        }
        else {
            holder.mRecycleItemBinding.setPlaceholder(R.drawable.top_learner_badge);
        }

        holder.mRecycleItemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        // return 0 if arrayList is null
        return mItemsHelperArrayList == null ? 0 : mItemsHelperArrayList.size();
    }

    // create a class that extends recyclerView ViewHolder to help populate and get our view
    public class RAVBindingHolder extends RecyclerView.ViewHolder {
        // Data-Binding layout generated class
        RecycleItemBinding mRecycleItemBinding;

        public RAVBindingHolder(@NonNull View itemView) {
            super(itemView);

            // bind to view
            mRecycleItemBinding = DataBindingUtil.bind(itemView);
        }
    }
}
