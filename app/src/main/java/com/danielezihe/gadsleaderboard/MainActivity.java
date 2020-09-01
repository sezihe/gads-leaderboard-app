package com.danielezihe.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    Button mSubmitProjectBtn;

    ViewPagerAdapter mViewPagerAdapter;

    LearningLeadersFrag mLearningLeadersFrag;
    SkillIQLeadersFrag mSkillIQLeadersFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize views
        mTabLayout = findViewById(R.id.mtabLayout);
        mViewPager = findViewById(R.id.mviewPager);
        mSubmitProjectBtn = findViewById(R.id.submitProjectBtn);

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

        // handle submitBtn clickListener
        handleSubmitBtnClickListener();

    }

    private void handleSubmitBtnClickListener() {
        mSubmitProjectBtn.setOnClickListener(new View.OnClickListener() {
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