package com.toring.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.toring.myapplication.R;
import com.toring.myapplication.fragment.AlbumListFragment;
import com.toring.myapplication.fragment.FacebookAlbumFragment;
import com.toring.myapplication.fragment.FragmentBase;
import com.toring.myapplication.fragment.NoFacebookFragment;
import com.toring.myapplication.fragment.P1ListFragment;
import com.toring.myapplication.fragment.P2GridFragment;
import com.toring.myapplication.fragment.P3SlideFragment;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.manager.ScreenManager;
import com.toring.myapplication.network.RetrofitFactory;
import com.toring.myapplication.network.facebook_model.Album;
import com.toring.myapplication.network.modle.DataObject;
import com.toring.myapplication.network.modle.MainObject;
import com.toring.myapplication.network.service.ServiceGetPicture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.AccessToken.getCurrentAccessToken;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AccessToken.getCurrentAccessToken() == null){
            ScreenManager.replaceFragment(this, R.id.full, new NoFacebookFragment(), false);
        }else {
            ScreenManager.replaceFragment(this, R.id.full, new FacebookAlbumFragment(), false);
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
        ScreenManager.replaceFragment(this, R.id.full, new FacebookAlbumFragment(), false);
    }

    public void logoutFacebook(){
        LoginManager.getInstance().logOut();
        ScreenManager.replaceFragment(this, R.id.full, new NoFacebookFragment(), false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
