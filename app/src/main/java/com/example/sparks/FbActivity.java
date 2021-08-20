package com.example.sparks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.squareup.picasso.Picasso;

public class FbActivity extends AppCompatActivity {
    private TextView info;
    private ImageView fbprof;
    private LoginButton fblog;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb);
        info = findViewById(R.id.info);
        fbprof = findViewById(R.id.fbprof);
        fblog = findViewById(R.id.fbLog);

        callbackManager = CallbackManager.Factory.create();

        fblog.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText("User Id: "+ loginResult.getAccessToken().getUserId());

                String imageURL = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() +"/picture?return_ssl_resources=1";
                Picasso.get().load(imageURL).into(fbprof);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    //@Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}