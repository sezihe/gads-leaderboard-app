package com.danielezihe.gadsleaderboard;

public class ItemsHelper {

    private String mname;
    private String mhours_or_score;
    private String mcountry;
    private String mbadgeURL;

    // constructor
    public ItemsHelper(String name, String hours_or_score, String country, String badgeURL)
    {
        mname = name;
        mhours_or_score = hours_or_score;
        mcountry = country;
        mbadgeURL = badgeURL;
    }

    // getters

    public String getMname() {
        return mname;
    }

    public String getMhours_skilliq() {
        return mhours_or_score;
    }

    public String getMcountry() {
        return mcountry;
    }

    public String getMbadgeURL() {
        return mbadgeURL;
    }
}
