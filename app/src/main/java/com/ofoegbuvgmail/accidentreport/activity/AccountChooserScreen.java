package com.ofoegbuvgmail.accidentreport.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.ofoegbuvgmail.accidentreport.R;

public class AccountChooserScreen extends AppCompatActivity implements View.OnClickListener {

    private Button userSignIn;
    private Button adminSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_chooser_screen);
        Toolbar toolbar = findViewById(R.id.app_toolbar);
        toolbar.setVisibility(View.GONE);
        setSupportActionBar(toolbar);

        userSignIn = findViewById(R.id.user_signIn);
        adminSignIn = findViewById(R.id.admin_signIn);
        userSignIn.setOnClickListener(this);
        adminSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.user_signIn:
                intent = new Intent(AccountChooserScreen.this, SignInActivity.class);
                //moveTaskToBack(true);
                startActivity(intent);
                break;
            case R.id.admin_signIn:
                intent = new Intent(AccountChooserScreen.this, AdminActivity.class);
                startActivity(intent);
                break;
        }
    }
}
