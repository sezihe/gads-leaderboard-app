package com.danielezihe.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.danielezihe.gadsleaderboard.databinding.ActivitySubmitProjectBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SubmitProjectActivity extends AppCompatActivity {

    private static final String URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

    // Data-Binding layout generated class
    ActivitySubmitProjectBinding mB;

    private String fname;
    private String lname;
    private String emailAddress;
    private String projectLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mB = DataBindingUtil.setContentView(SubmitProjectActivity.this, R.layout.activity_submit_project);

        mB.submitProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitProject();
            }
        });

        handleTopBackButtonClick();
    }

    private void submitProject() {
        // create a volley request and send the jsonObject
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("entry.1877115667", getFname());
                params.put("entry.2006916086", getLname());
                params.put("entry.1824927963", getEmailAddress());
                params.put("entry.284483984", getProjectLink());

                return params;
            }
        };


    }

    private void handleTopBackButtonClick() {
        mB.topBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mB.topBackButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                    mB.topBackButton.setColorFilter(getColor(R.color.lighter_dark_yellow), PorterDuff.Mode.SRC_IN);
                } else if(motionEvent.getAction() == motionEvent.ACTION_UP) {
                    mB.topBackButton.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_IN);
                    mB.topBackButton.performClick();
                }
                return true;
            }
        });
    }

    // getters

    public String getFname() {
        return String.valueOf(Objects.requireNonNull(mB.fieldFname.getEditText()).getText()).trim();
    }

    public String getLname() {
        return String.valueOf(Objects.requireNonNull(mB.fieldLname.getEditText()).getText()).trim();
    }

    public String getEmailAddress() {
        return String.valueOf(Objects.requireNonNull(mB.fieldEmail.getEditText()).getText()).trim();
    }

    public String getProjectLink() {
        return String.valueOf(Objects.requireNonNull(mB.fieldGithubLink.getEditText()).getText()).trim();
    }
}