package com.toring.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.toring.myapplication.R;
import com.toring.myapplication.fragment.FacebookFragment;
import com.toring.myapplication.fragment.ViewAllImageFragment;
import com.toring.myapplication.manager.ScreenManager;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AccessToken.getCurrentAccessToken() == null){
            ScreenManager.replaceFragment(this, R.id.full, new ViewAllImageFragment(), false);
        }else {
            ScreenManager.replaceFragment(this, R.id.full, new FacebookFragment(), false);
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginDone();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    public void loginFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(
                "public_profile", "user_photos", "user_friends"));
    }

    private void loginDone() {
        ScreenManager.replaceFragment(this, R.id.full, new FacebookFragment(), false);
    }

    public void logoutFacebook(){
        LoginManager.getInstance().logOut();
        ScreenManager.replaceFragment(this, R.id.full, new ViewAllImageFragment(), false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
