package com.danielezihe.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.danielezihe.gadsleaderboard.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapter mViewPagerAdapter;

    LearningLeadersFrag mLearningLeadersFrag;
    SkillIQLeadersFrag mSkillIQLeadersFrag;

    // Data-Binding layout generated class
    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        // initialize viewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

        // initialize fragments
        mLearningLeadersFrag = new LearningLeadersFrag();
        mSkillIQLeadersFrag = new SkillIQLeadersFrag();

        // set Fragments to viewPagerAdapter
        addFragments();

        // set viewPager Adapter
        mActivityMainBinding.mviewPager.setAdapter(mViewPagerAdapter);

        // assign viewPager to tabLayout
        mActivityMainBinding.mtabLayout.setupWithViewPager(mActivityMainBinding.mviewPager);

        // handle submitBtn clickListener
        handleSubmitBtnClickListener();

    }

    private void handleSubmitBtnClickListener() {
        mActivityMainBinding.submitProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitProjectActivity = new Intent(getApplicationContext(), SubmitProjectActivity.class);
                startActivity(submitProjectActivity);
            }
        });
    }

    private void addFragments() {
        mViewPagerAdapter.addFragment(mLearningLeadersFrag, "Learning Leaders");
        mViewPagerAdapter.addFragment(mSkillIQLeadersFrag, "Skill IQ Leaders");
    }
}