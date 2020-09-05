package com.danielezihe.gadsleaderboard;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class SubmitProjectActivityViewModel extends BaseObservable {

    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PROJECT_LINK = "PROJECT_LINK";

    public String fname;
    public String lname;
    public String emailAddress;
    public String projectLink;

    InterfaceHelper mIh;

    // constructor
    public SubmitProjectActivityViewModel(InterfaceHelper ih) {
        mIh = ih;
        fname = "";
        lname = "";
        emailAddress = "";
        projectLink = "";
    }

    // getters
    @Bindable
    public String getFname() {
        return this.fname;
    }

    @Bindable
    public String getLname() {
        return this.lname;
    }

    @Bindable
    public String getEmailAddress() {
        return this.emailAddress;
    }

    @Bindable
    public String getProjectLink() {
        return this.projectLink;
    }

    // setters
    public void setFname(String fName) {
        this.fname = fName;
        notifyPropertyChanged(BR.fname);

        // call the validateEditText Interface
        mIh.onValidateEditText(FIRST_NAME);
    }

    public void setLname(String lName) {
        this.lname = lName;
        notifyPropertyChanged(BR.lname);

        // call the validateEditText Interface
        mIh.onValidateEditText(LAST_NAME);
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
        notifyPropertyChanged(BR.emailAddress);

        // call the validateEditText Interface
        mIh.onValidateEditText(EMAIL);
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
        notifyPropertyChanged(BR.projectLink);

        // call the validateEditText Interface
        mIh.onValidateEditText(PROJECT_LINK);
    }
}
