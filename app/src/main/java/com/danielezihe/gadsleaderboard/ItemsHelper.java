package com.danielezihe.gadsleaderboard;

import android.content.Context;

public class ItemsHelper {

    private String mname;
    private String mhours_or_score;
    private String mcountry;
    private String mbadgeURL;
    private boolean mIsSkillIQ;

    private Context mContext;

    // constructor
    public ItemsHelper(Context context, String name, String hours_or_score, String country, String badgeURL, boolean isSkillIQ) {
        mContext = context;
        mname = name;
        mhours_or_score = hours_or_score;
        mcountry = country;
        mbadgeURL = badgeURL;
        mIsSkillIQ = isSkillIQ;
    }

    // getters:
    public String getMname() {
        return mname;
    }

    public String getMhours_score() {
        return mhours_or_score;
    }

    public String getMcountry() {
        return mcountry;
    }

    public String getMbadgeURL() {
        return mbadgeURL;
    }

    public boolean getMisSkillIQ() { return mIsSkillIQ; }

    public String getSubText() {
        return getMisSkillIQ() ? getMhours_score() + " " + mContext.getResources().getString(R.string.skill_iq_score) +", " + getMcountry() + "." : getMhours_score() + " " + mContext.getResources().getString(R.string.learning_hours) + ", " + getMcountry() + ".";
    }
}
