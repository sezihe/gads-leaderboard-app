package com.danielezihe.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RAViewHolder> {

    private Context mContext;
    private ArrayList<ItemsHelper> mItemsHelperArrayList;

    // create a constructor
    public RecyclerAdapter(Context context, ArrayList<ItemsHelper> itemsHelperArrayList) {
        mContext = context;
        mItemsHelperArrayList = itemsHelperArrayList;
    }

    @NonNull
    @Override
    public RAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate layout with recycler item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        return new RAViewHolder(view);
    }

    // bind data from Items ArrayList to our views
    @Override
    public void onBindViewHolder(@NonNull RAViewHolder holder, int position) {
        ItemsHelper itemsHelper = mItemsHelperArrayList.get(position);

        // use picasso to load url into the imageView. Also set the local directory image as a place holder
        Picasso.get().load(itemsHelper.getMbadgeURL()).placeholder(itemsHelper.iconSelector()).into(holder.mItemLogo);

        // set other values
        holder.setNameTextView(itemsHelper.getMname());
        holder.setAchievementTextView(itemsHelper.getSubText());
    }

    @Override
    public int getItemCount() {
        return mItemsHelperArrayList.size();
    }

    // create a class that extends recyclerView ViewHolder to help populate and get our view
    public class RAViewHolder extends RecyclerView.ViewHolder {
        public ImageView mItemLogo;
        private TextView mNameTextView;
        private TextView mAchievementTextView;

        public RAViewHolder(@NonNull View itemView) {
            super(itemView);

            // initialize views
            mItemLogo = itemView.findViewById(R.id.itemLogo);
            mNameTextView = itemView.findViewById(R.id.nameText);
            mAchievementTextView = itemView.findViewById(R.id.achievementText);
        }

        // setters
        public void setNameTextView(String nameTextView) {
            mNameTextView.setText(nameTextView);
        }

        public void setAchievementTextView(String achievementTextView) {
            mAchievementTextView.setText(achievementTextView);
        }
    }
}
