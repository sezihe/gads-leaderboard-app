package com.danielezihe.gadsleaderboard;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.danielezihe.gadsleaderboard.databinding.ActivitySubmitProjectBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SubmitProjectActivity extends AppCompatActivity implements InterfaceHelper {

    private static final String URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    private static final String SUBMIT_PROJECT_REQUEST = "SUBMIT_PROJECT_REQUEST";

    // Data-Binding layout generated class
    ActivitySubmitProjectBinding mB;

    SubmitProjectActivityViewModel mSubmitProjectActivityViewModel;
    ConfirmAlertDialog mConfirmAlertDialog;
    SuccessErrorAlertDialog mSuccessErrorAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mB = DataBindingUtil.setContentView(SubmitProjectActivity.this, R.layout.activity_submit_project);

        mSubmitProjectActivityViewModel = new SubmitProjectActivityViewModel(this);
        mB.setSubmitProActViewModel(mSubmitProjectActivityViewModel);

        mConfirmAlertDialog = new ConfirmAlertDialog(this, this);

        mB.submitProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add a safety check on all fields
                if (validateFName() | validateLName() | validateEmail() | validateProjectLink()) {
                    Objects.requireNonNull(mConfirmAlertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mConfirmAlertDialog.show();
                }
            }
        });

        handleTopBackButtonClick();
    }

    private void submitProject() {
        // create a volley request and send the jsonObject
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("drexx", "onResponse: " + response);
                // if success
                mSuccessErrorAlertDialog = new SuccessErrorAlertDialog(SubmitProjectActivity.this, "good");
                Objects.requireNonNull(mSuccessErrorAlertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mSuccessErrorAlertDialog.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error
                mSuccessErrorAlertDialog = new SuccessErrorAlertDialog(SubmitProjectActivity.this, "bad");
                Objects.requireNonNull(mSuccessErrorAlertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mSuccessErrorAlertDialog.show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("entry.1877115667", getFname());
                params.put("entry.2006916086", getLname());
                params.put("entry.1824927963", getEmailAddress());
                params.put("entry.284483984", getProjectLink());

                return params;
            }
        };

        // set retry policy, tag and add to singleton request que
        stringRequest.setRetryPolicy(new DefaultRetryPolicy());
        stringRequest.setTag(SUBMIT_PROJECT_REQUEST);
        MySingleTon.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("fname", getFname());
        outState.putString("lname", getLname());
        outState.putString("email", getEmailAddress());
        outState.putString("projectLink", getProjectLink());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mSubmitProjectActivityViewModel.setFname(savedInstanceState.getString("fname"));
        mSubmitProjectActivityViewModel.setLname(savedInstanceState.getString("lname"));
        mSubmitProjectActivityViewModel.setEmailAddress(savedInstanceState.getString("email"));
        mSubmitProjectActivityViewModel.setProjectLink(savedInstanceState.getString("projectLink"));
    }

    private void handleTopBackButtonClick() {
        mB.topBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mB.topBackButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                    mB.topBackButton.setColorFilter(getColor(R.color.lighter_dark_yellow), PorterDuff.Mode.SRC_IN);
                } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    mB.topBackButton.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_IN);
                    mB.topBackButton.performClick();
                }
                return true;
            }
        });
    }

    // getters
    public String getFname() {
        return mSubmitProjectActivityViewModel.getFname().trim();
    }

    public String getLname() {
        return mSubmitProjectActivityViewModel.getLname().trim();
    }

    public String getEmailAddress() {
        return mSubmitProjectActivityViewModel.getEmailAddress().trim();
    }

    public String getProjectLink() {
        return mSubmitProjectActivityViewModel.getProjectLink().trim();
    }

    // would be called every time an editText is updated
    @Override
    public void onValidateEditText(String editTextName) {
        // using SubmitProjectActivityViewModel import to access it's static variables
        if (editTextName.equals(SubmitProjectActivityViewModel.FIRST_NAME)) {
            validateFName();
        } else if (editTextName.equals(SubmitProjectActivityViewModel.LAST_NAME)) {
            validateLName();
        } else if (editTextName.equals(SubmitProjectActivityViewModel.EMAIL)) {
            validateEmail();
        } else if (editTextName.equals(SubmitProjectActivityViewModel.PROJECT_LINK)) {
            validateProjectLink();
        }
    }

    // would be called when submit is confirmed
    @Override
    public void onConfirmSubmitClick() {
        // confirmed submit project
        submitProject();
    }

    // validate First Name field and set error if needed
    boolean validateFName() {
        if (getFname().isEmpty()) {
            mB.fieldFname.setError("First Name cannot be empty");
            return false;
        } else {
            mB.fieldFname.setError(null);
            mB.fieldFname.setErrorEnabled(false);
            return true;
        }
    }

    // validate Last Name field and set error if needed
    boolean validateLName() {
        if (getLname().isEmpty()) {
            mB.fieldLname.setError("Last Name cannot be empty");
            return false;
        } else {
            mB.fieldLname.setError(null);
            mB.fieldLname.setErrorEnabled(false);
            return true;
        }
    }

    // validate Email field and set error if needed
    boolean validateEmail() {
        boolean valid = android.util.Patterns.EMAIL_ADDRESS.matcher(getEmailAddress()).matches();
        if (getEmailAddress().isEmpty()) {
            mB.fieldEmail.setError("Email Address cannot be empty");
            return false;
        } else if (!valid) {
            mB.fieldEmail.setError("Invalid Email Address");
            return false;
        } else {
            mB.fieldEmail.setError(null);
            mB.fieldEmail.setErrorEnabled(false);
            return true;
        }
    }

    // validate GitHub Link field and set error if needed
    boolean validateProjectLink() {
        boolean valid = URLUtil.isValidUrl(getProjectLink());
        if (getProjectLink().isEmpty()) {
            mB.fieldGithubLink.setError("Github Link cannot be empty");
            return false;
        } else if (!valid) {
            mB.fieldGithubLink.setError("Invalid URL");
            return false;
        } else {
            mB.fieldGithubLink.setError(null);
            mB.fieldGithubLink.setErrorEnabled(false);
            return true;
        }
    }
}