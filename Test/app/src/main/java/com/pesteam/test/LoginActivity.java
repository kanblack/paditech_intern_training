package com.pesteam.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;
    @BindView(R.id.image1)
    ImageView imageView1;
    @BindView(R.id.image2)
    ImageView imageView2;
    @BindView(R.id.image3)
    ImageView imageView3;
    @BindView(R.id.image4)
    ImageView imageView4;
    ArrayList<String> strings = new ArrayList<>();
    ArrayList<String> strings_url = new ArrayList<>();
    AccessToken accessToken;
    int k = 0;

    ImageView[] imageViews = new ImageView[]{
            imageView1,
            imageView2,
            imageView3,
            imageView4
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile", "user_photos");
        callbackManager = CallbackManager.Factory.create();


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                String userID = String.valueOf(loginResult.getAccessToken());
                accessToken = loginResult.getAccessToken();
                Log.e("onSuccess: ", userID);
                Log.e("onSuccess: ", accessToken.toString() );
                Log.e("onSuccess: ", accessToken.getToken() );
                Log.e("onSuccess: ", accessToken.getUserId() );

                final GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.e("onCompleted: ", object.toString());
                                try {
                                    JSONArray jsonArray = object.getJSONObject("albums").getJSONArray("data");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                        JSONObject cover_photo = jsonObject.getJSONObject("cover_photo");
                                        strings.add(cover_photo.getString("id"));
                                        Log.e("name: ", jsonObject.getString("name"));
                                        Log.e("id: ", jsonObject.getString("id"));
                                        setimage(i);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "albums{cover_photo,name}");
                request.setParameters(parameters);

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GraphResponse gResponse = request.executeAndWait();
                    }
                });
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    private void setimage(final int i) {
        String id = "/"+ strings.get(i);
        final GraphRequest request1 = GraphRequest.newGraphPathRequest(accessToken,id, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                Log.e("onCompleted: ", response.toString() );
                try {
                    String link_image = response.getJSONObject().getJSONArray("images").getJSONObject(0).getString("source");
                    Log.e("onCompleted: ", link_image );
                    strings_url.add(link_image);
                    GlideApp.with(LoginActivity.this).load(strings_url.get(i)).into(imageViews[i]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
//

        Bundle parameters = new Bundle();
        parameters.putString("fields", "images");
        request1.setParameters(parameters);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                GraphResponse gResponse = request1.executeAndWait();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displayUserInfo(JSONObject object) {
        Log.e("displayUserInfo: ", object.toString());
        String first_name = "", last_name = "", email = "", id = "";
        try {
            email = object.getString("email");
            id = object.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("email", email);
        Log.e("id: ", id);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
