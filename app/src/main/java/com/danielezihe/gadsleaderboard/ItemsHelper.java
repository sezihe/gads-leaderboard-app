package com.danielezihe.gadsleaderboard;

public class ItemsHelper {

    private String mname;
    private String mhours_or_score;
    private String mcountry;
    private String mbadgeURL;
    private boolean mIsSkillIQ;

    // constructor
    public ItemsHelper(String name, String hours_or_score, String country, String badgeURL, boolean isSkillIQ) {
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


    public int iconSelector() {
        return mIsSkillIQ ? R.drawable.skill_iq_trimmed : R.drawable.top_learner_badge;
    }

    public String getSubText() {
        return mIsSkillIQ ? getMhours_score() + " skill IQ Score, " + getMcountry() + "." : getMhours_score() + " learning hours, " + getMcountry() + ".";
    }
}
