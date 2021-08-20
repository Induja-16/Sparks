package com.example.sparks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    SignInButton signInButton;
    //    private TextView info;
//    private ImageView fbprof;
//    private LoginButton fblog;
//    CallbackManager callbackManager;
    private GoogleApiClient googleApiClient;
    private  Button fb;
    private static final int SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        signInButton = findViewById(R.id.google_signbtn);
        fb=findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openActivity2();
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN)
                ;


            }
        });
//        info = findViewById(R.id.info);
//        fbprof = findViewById(R.id.fbprof);
//        fblog = findViewById(R.id.fbLog);
//        callbackManager = CallbackManager.Factory.create();
//
//
//        fblog.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                info.setText("User Id: "+ loginResult.getAccessToken().getUserId());
//                String imageURL = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() +"/picture?return_ssl_resources=1";
//                Picasso.get().load(imageURL).into(fbprof);
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
  }
//
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

}
    @Override
      protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
           super.onActivityResult(requestCode, resultCode, data);
            // callbackManager.onActivityResult(requestCode,resultCode,data);

            if (requestCode == SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }

            }


            //callbackManager.onActivityResult(requestCode,resultCode,data);

        }
        public void openActivity2(){
        Intent intent = new Intent(this, FbActivity.class);
        startActivity(intent);
        }
    }

