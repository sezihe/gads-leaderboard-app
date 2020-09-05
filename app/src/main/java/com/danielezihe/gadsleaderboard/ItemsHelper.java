package com.danielezihe.gadsleaderboard;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class ItemsHelper implements Parcelable {

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

    // second constructor
    public ItemsHelper(Parcel parcel) {
        mname = parcel.readString();
        mhours_or_score = parcel.readString();
        mcountry = parcel.readString();
        mbadgeURL = parcel.readString();
        mIsSkillIQ = parcel.readByte() != 0;
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

    // parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // write variables to parcel to be saved
        parcel.writeString(getMname());
        parcel.writeString(getMhours_score());
        parcel.writeString(getMcountry());
        parcel.writeString(getMbadgeURL());
        parcel.writeInt(getMisSkillIQ() ? 1 : 0);
        parcel.writeString(getSubText());
    }

    public static final Creator<ItemsHelper> CREATOR = new Creator<ItemsHelper>() {
        @Override
        public ItemsHelper createFromParcel(Parcel parcel) {
            return new ItemsHelper(parcel);
        }

        @Override
        public ItemsHelper[] newArray(int i) {
            return new ItemsHelper[i];
        }
    };
}
