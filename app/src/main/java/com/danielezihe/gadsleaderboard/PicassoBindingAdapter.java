package com.danielezihe.gadsleaderboard;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class PicassoBindingAdapter {

    @BindingAdapter({"imageUrl", "placeholder"})
    public static void setImage(ImageView imageView, String imageUrl, int placeholder) {
        // use picasso to load url into the imageView. Also set the local directory image provided as a place holder
        Picasso.get().load(imageUrl)
                .placeholder(placeholder)
                .into(imageView);

    }

}
