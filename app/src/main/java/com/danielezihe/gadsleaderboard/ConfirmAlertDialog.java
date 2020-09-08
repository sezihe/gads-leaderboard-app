package com.danielezihe.gadsleaderboard;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.danielezihe.gadsleaderboard.databinding.ConfirmSubmitAlertDialogLayoutBinding;

public class ConfirmAlertDialog extends Dialog implements View.OnClickListener {

    Activity mActivity;

    ConfirmSubmitAlertDialogLayoutBinding mB;

    InterfaceHelper mIh;

    public ConfirmAlertDialog(Activity activity, InterfaceHelper ih) {
        super(activity);
        mActivity = activity;
        mIh = ih;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_submit_alert_dialog_layout);
        mB = ConfirmSubmitAlertDialogLayoutBinding.bind(findViewById(R.id.main_csad));
        mB.confirmSubmitBtn.setOnClickListener(this);
        mB.cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_submit_btn:
                // call interface
                mIh.onConfirmSubmitClick();
                dismiss();
                break;
            case R.id.cancel_btn:
                dismiss();
                break;
        }
    }
}
