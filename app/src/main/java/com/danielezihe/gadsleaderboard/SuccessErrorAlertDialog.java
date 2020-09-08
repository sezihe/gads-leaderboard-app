package com.danielezihe.gadsleaderboard;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import com.danielezihe.gadsleaderboard.databinding.SuccessErrorDialogLayoutBinding;

public class SuccessErrorAlertDialog extends Dialog {

    Activity mActivity;
    String mDisplayStatus;


    SuccessErrorDialogLayoutBinding mB;

    public SuccessErrorAlertDialog(Activity activity, String displayStatus) {
        super(activity);
        mActivity = activity;
        mDisplayStatus = displayStatus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_error_dialog_layout);
        mB = SuccessErrorDialogLayoutBinding.bind(findViewById(R.id.main_sed));
        mB.setDisplay(mDisplayStatus);
    }
}
