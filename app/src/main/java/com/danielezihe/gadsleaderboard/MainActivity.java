package com.danielezihe.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;

    ViewPagerAdapter mViewPagerAdapter;

    LearningLeadersFrag mLearningLeadersFrag;
    SkillIQLeadersFrag mSkillIQLeadersFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign views
        mTabLayout = findViewById(R.id.mtabLayout);
        mViewPager = findViewById(R.id.mviewPager);

        // initialize viewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

        // initialize fragments
        mLearningLeadersFrag = new LearningLeadersFrag();
        mSkillIQLeadersFrag = new SkillIQLeadersFrag();

        // set Fragments to viewPagerAdapter
        addFragments();

        // set viewPager Adapter
        mViewPager.setAdapter(mViewPagerAdapter);

        // assign viewPager to tabLayout
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void addFragments() {
        mViewPagerAdapter.addFragment(mLearningLeadersFrag, "Learning Leaders");
        mViewPagerAdapter.addFragment(mSkillIQLeadersFrag, "Skill IQ Leaders");

    }
}