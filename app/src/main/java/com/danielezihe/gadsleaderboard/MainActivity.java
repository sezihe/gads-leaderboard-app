package com.danielezihe.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.danielezihe.gadsleaderboard.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapter mViewPagerAdapter;

    LearningLeadersFrag mLearningLeadersFrag;
    SkillIQLeadersFrag mSkillIQLeadersFrag;

    boolean isSKillIqLeadersTryingRetry = false;
    Snackbar snackbarllf, snackbarsilf;

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

        // create splash screen
        createSplashScreen();

        // handle splash screen timeout
        handleSplashScreenTimeOut();

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

    private void createSplashScreen() {
        setSplashVisiblity(true);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void handleSplashScreenTimeOut() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                removeSplashScreen();
            }
        }, 3000);
    }

    public void setSplashVisiblity(boolean visible) {
        mActivityMainBinding.setSplashVisible(visible);
    }

    public void removeSplashScreen() {
        if (mActivityMainBinding.getSplashVisible()) {
            setSplashVisiblity(false);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    public void retry(String fragment) {
        if (fragment.equals("llf")) {
            // switch viewpager to the page
            mActivityMainBinding.mviewPager.setCurrentItem(0, true);

            // make snackBar
            snackbarllf = Snackbar.make(mActivityMainBinding.getRoot(), "Cannot connect to server. Please check your internet connection and retry", Snackbar.LENGTH_INDEFINITE);
            snackbarllf.setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // perform retry
                    mLearningLeadersFrag.mLearningLeadersFragBinding.progressBarLlf.setVisibility(View.VISIBLE);
                    mLearningLeadersFrag.getAndPopulateTopLearnersFromAPI();
                    mLearningLeadersFrag.retryCount = 0;

                    // check if skillIqLeadersFragment is also trying to retry. if yes, perform retry.
                    if (isSKillIqLeadersTryingRetry) {
                        mSkillIQLeadersFrag.mActivitySkillIqLeadersFragBinding.progressBarSilf.setVisibility(View.VISIBLE);
                        mSkillIQLeadersFrag.getAndPopulateTopSkillIqFromAPI();
                        mSkillIQLeadersFrag.retryCount = 0;
                    }
                    snackbarllf.dismiss();
                }
            });
            snackbarllf.show();
        } else if (fragment.equals("silf")) {
            // check if learningLeadersFragment's retry snackBar is already being shown. If yes, just add the retry request to it.
            if (snackbarllf.isShown()) {
                isSKillIqLeadersTryingRetry = true;
            } else {
                // switch viewpager to the page
                mActivityMainBinding.mviewPager.setCurrentItem(1, true);

                // make snackBar
                snackbarsilf = Snackbar.make(mActivityMainBinding.getRoot(), "Cannot connect to server. Please check your internet connection and retry", Snackbar.LENGTH_INDEFINITE);
                snackbarsilf.setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // perform retry
                        mSkillIQLeadersFrag.mActivitySkillIqLeadersFragBinding.progressBarSilf.setVisibility(View.VISIBLE);
                        mSkillIQLeadersFrag.getAndPopulateTopSkillIqFromAPI();
                        mSkillIQLeadersFrag.retryCount = 0;

                        snackbarsilf.dismiss();
                    }
                });
                snackbarsilf.show();
            }
        }
    }
}