package com.toring.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.toring.myapplication.R;
import com.toring.myapplication.adapter.P2GridAdapter;
import com.toring.myapplication.fragment.P1ListFragment;
import com.toring.myapplication.fragment.P2GridFragment;
import com.toring.myapplication.fragment.P3SlideFragment;
import com.toring.myapplication.manager.ScreenManager;
import com.toring.myapplication.network.RetrofitFactory;
import com.toring.myapplication.network.modle.DataObject;
import com.toring.myapplication.network.modle.MainObject;
import com.toring.myapplication.network.service.ServiceGetPicture;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<String> pictureList;
    private ImageView ivChangeMode;
    private ImageView btLoginFace;

    private int modeVIew = 0;
    private Fragment currentFragment;
    private int iconChangeMode = R.drawable.ic_apps_white_24dp;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());

        ivChangeMode = this.findViewById(R.id.iv_change_mode);
        btLoginFace = this.findViewById(R.id.bt_login_face);

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("OK", "onSuccess: " + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        ivChangeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMode();
            }
        });

        btLoginFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() == null) {
                    loginFacebook();
                } else {
                    loginDone();
                }
            }
        });

        getData();
    }

    private void loginDone() {
        final GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + AccessToken.getCurrentAccessToken().getUserId(),
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.e("OKKOKO", "onCompleted: " + response.getJSONObject().toString());
                    }
                });

        Bundle bundle = new Bundle();
        bundle.putString("fields", "id, name");
        request.setParameters(bundle);
        request.executeAsync();

        Uri s = Profile.getCurrentProfile().getProfilePictureUri(500, 500);


        GraphRequest request1 = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + AccessToken.getCurrentAccessToken().getUserId() + "/albums",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.e("OKOKOKOKOOOKO", "onCompleted: " + response.getJSONObject().toString());
                    }
                }
        );

        Bundle bundle1 = new Bundle();
        bundle.putString("fields", "id, name");
        request1.setParameters(bundle1);
        request1.executeAsync();
    }

    private void getData() {
        final Realm realm = Realm.getDefaultInstance();
        List<DataObject> myData = realm.where(DataObject.class).findAll();
        if (myData.size() != 0) {
            pictureList = new ArrayList<>();
            for (DataObject dataObject : myData) {
                pictureList.add(dataObject.getUrl());
            }
            P1ListFragment p1ListFragment = new P1ListFragment();
            p1ListFragment.setPictureList(pictureList);
            ScreenManager.replaceFragment(MainActivity.this,
                    R.id.content,
                    p1ListFragment,
                    false);
        } else {
            ServiceGetPicture getPicture = RetrofitFactory.getInstance().createService(ServiceGetPicture.class);
            getPicture.getPicture()
                    .enqueue(new Callback<MainObject>() {
                        @Override
                        public void onResponse(Call<MainObject> call, Response<MainObject> response) {
                            Log.d("main", "onResponse: " + response.body().toString());
                            MainObject mainObject = response.body();
                            pictureList = mainObject.getData();

                            P1ListFragment p1ListFragment = new P1ListFragment();
                            p1ListFragment.setPictureList(pictureList);
                            ScreenManager.replaceFragment(MainActivity.this,
                                    R.id.content,
                                    p1ListFragment,
                                    false);

                            realm.beginTransaction();
                            for (String s : pictureList) {
                                realm.copyToRealm(new DataObject(s));
                            }
                            realm.commitTransaction();
                            realm.close();
                        }

                        @Override
                        public void onFailure(Call<MainObject> call, Throwable t) {
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.dialog);
                            alertDialog.setMessage("Kết nối thất bại. Vui lòng kiểm tra lại kết nối internet.");
                            alertDialog.setPositiveButton("Tải lại", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    MainActivity.this.getData();
                                }
                            });

                            alertDialog.setNegativeButton("Đóng", null);
                            alertDialog.setNeutralButton("Đi đến cài đặt", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                }
                            });

                            alertDialog.show();
                        }
                    });
        }
    }

    private void changeMode() {
        if (pictureList != null) {
            switch (modeVIew) {
                case 0: {
                    iconChangeMode = R.drawable.ic_slideshow_white_24dp;
                    P2GridFragment p2GridFragment = new P2GridFragment();
                    p2GridFragment.setPictureList(pictureList);
                    currentFragment = p2GridFragment;
                    break;
                }

                case 1: {
                    iconChangeMode = R.drawable.ic_view_list_white_24dp;
                    P3SlideFragment p3SlideFragment = new P3SlideFragment();
                    p3SlideFragment.setPictureList(pictureList);
                    currentFragment = p3SlideFragment;
                    break;
                }

                case 2: {
                    iconChangeMode = R.drawable.ic_apps_white_24dp;
                    P1ListFragment p1ListFragment = new P1ListFragment();
                    p1ListFragment.setPictureList(pictureList);
                    currentFragment = p1ListFragment;
                    modeVIew = -1;
                    break;
                }
            }

            ivChangeMode.setImageResource(iconChangeMode);

            ScreenManager.replaceFragment(MainActivity.this,
                    R.id.content,
                    currentFragment,
                    false);

            modeVIew++;
        }
    }

    private void loginFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(
                "public_profile", "user_photos", "user_friends", "profile_pic"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
