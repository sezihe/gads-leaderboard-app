package com.danielezihe.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> mFragmentArrayList = new ArrayList<>();
    private ArrayList<String> mFragmentTitle = new ArrayList<>();

    // constructor
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }

    // add fragments to fragment arrayList
    public void addFragment(Fragment frag, String fragTitile) {
        mFragmentArrayList.add(frag);
        mFragmentTitle.add(fragTitile);
    }
}
