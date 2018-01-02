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
    private List<String> pictureList;
    private ImageView ivChangeMode;
    private ImageView btLoginFace, ivBack;
    private TextView tvLogout, tvTitle;
    private LoginButton loginButton;

    private int modeVIew = 0;
    private Fragment currentFragment;
    private int iconChangeMode = R.drawable.ic_apps_white_24dp;

    private CallbackManager callbackManager;
    private View.OnClickListener albumClick;

    private boolean isFacebook = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivChangeMode = this.findViewById(R.id.iv_change_mode);
        btLoginFace = this.findViewById(R.id.bt_login_face);
        ivBack = findViewById(R.id.iv_back);
        tvLogout = this.findViewById(R.id.tv_logout);
        tvTitle = this.findViewById(R.id.tv_title);
        loginButton = this.findViewById(R.id.login_button);

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

        setEvent();

        if (getCurrentAccessToken() == null) {
            getDataNotLogin();
        } else {
            loginDone();
        }

        albumClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btLoginFace.setVisibility(View.GONE);
                ivBack.setVisibility(View.VISIBLE);
                tvLogout.setVisibility(View.GONE);
                ivChangeMode.setVisibility(View.VISIBLE);
                tvTitle.setText(((Album) view.getTag()).getName());
                getAlbumPhoto(((Album) view.getTag()).getId());
            }
        };
    }

    private void getAlbumPhoto(String albumID) {
        new GraphRequest(
                getCurrentAccessToken(),
                "/" + albumID + "/photos",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            pictureList = new ArrayList<>();
                            JSONArray temp = response.getJSONObject().getJSONArray("data");
                            for (int i = 0; i < temp.length(); i++) {
                                temp.get(i);
                                pictureList.add(getImageUrlById(((JSONObject) temp.get(i)).getString("id"), 500));
                            }
                            P1ListFragment p1ListFragment = new P1ListFragment();
                            p1ListFragment.setPictureList(pictureList);
                            p1ListFragment.setFacebook(isFacebook);
                            currentFragment = p1ListFragment;
                            ScreenManager.replaceFragment(MainActivity.this,
                                    R.id.content,
                                    currentFragment, true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    public static String getImageUrlById(String id, int expectWidth) {
        if (AccessToken.getCurrentAccessToken() != null) {
            String accessToken = getCurrentAccessToken().getToken();
            return String.format("https://graph.facebook.com/%s/picture?width=%d&access_token=%s", id, expectWidth, accessToken);
        }
        return null;
    }

    private void setEvent() {
        btLoginFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getCurrentAccessToken() == null) {
                    loginFacebook();
                } else {
                    loginDone();
                }
            }
        });

        ivChangeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMode();
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                changeUIWithLogin(false);
                getDataNotLogin();
                isFacebook = false;
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onBackPressed();
            }
        });
    }

    private void loginFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(
                "public_profile", "user_photos", "user_friends"));
//        loginButton.performClick();

    }

    private void loginDone() {
        isFacebook = true;
        getProfile();

        changeUIWithLogin(true);

        getDataLogin();
    }

    private void getProfile() {
        GraphRequest request = GraphRequest.newGraphPathRequest(
                getCurrentAccessToken(),
                "/" + getCurrentAccessToken().getUserId(),
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        DisplayPicture.displayImageCircleCrop(MainActivity.this,
                                Profile.getCurrentProfile().getProfilePictureUri(500, 500).toString(),
                                btLoginFace);
                        tvTitle.setText(Profile.getCurrentProfile().getName());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "profile_pic");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void getDataLogin() {
        getListAlbum();
    }

    private void getListAlbum() {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, name");
        GraphRequest request = new GraphRequest(
                getCurrentAccessToken(),
                "/" + getCurrentAccessToken().getUserId() + "/albums",
                parameters,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            ArrayList<Album> arrayList = new ArrayList<>();
                            JSONArray albums = response.getJSONObject().getJSONArray("data");
                            for (int j = 0; j < albums.length(); j++) {
                                JSONObject object = (JSONObject) albums.get(j);
                                Album album = new Album();
                                album.setId(object.getString("id"));
                                album.setName(object.getString("name"));
                                arrayList.add(album);
                            }
                            AlbumListFragment albumListFragment = new AlbumListFragment();
                            albumListFragment.setOnClickListener(albumClick);
                            albumListFragment.setAlbumList(arrayList);
                            currentFragment = albumListFragment;
                            ScreenManager.replaceFragment(MainActivity.this, R.id.content,
                                    currentFragment, false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        request.executeAsync();
    }

    private void changeUIWithLogin(boolean b) {
        if (b) {
            ivChangeMode.setVisibility(View.GONE);
            tvLogout.setVisibility(View.VISIBLE);
        } else {
            ivChangeMode.setVisibility(View.VISIBLE);
            tvLogout.setVisibility(View.GONE);
            DisplayPicture.displayImage(this,
                    R.drawable.ic_facebook,
                    btLoginFace);

            tvTitle.setText(getResources().getText(R.string.app_name));
        }
    }


    private void getDataNotLogin() {
        final Realm realm = Realm.getDefaultInstance();
        List<DataObject> myData = realm.where(DataObject.class).findAll();
        if (myData.size() != 0) {
            pictureList = new ArrayList<>();
            for (DataObject dataObject : myData) {
                pictureList.add(dataObject.getUrl());
            }
            P1ListFragment p1ListFragment = new P1ListFragment();
            p1ListFragment.setPictureList(pictureList);
            currentFragment = p1ListFragment;
            ScreenManager.replaceFragment(MainActivity.this,
                    R.id.content,
                    currentFragment,
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
                            currentFragment = p1ListFragment;
                            ScreenManager.replaceFragment(MainActivity.this,
                                    R.id.content,
                                    currentFragment,
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
                                    MainActivity.this.getDataNotLogin();
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
                    p2GridFragment.setFacebook(isFacebook);
                    currentFragment = p2GridFragment;
                    break;
                }

                case 1: {
                    iconChangeMode = R.drawable.ic_view_list_white_24dp;
                    P3SlideFragment p3SlideFragment = new P3SlideFragment();
                    p3SlideFragment.setPictureList(pictureList);
                    p3SlideFragment.setFacebook(isFacebook);
                    currentFragment = p3SlideFragment;
                    break;
                }

                case 2: {
                    iconChangeMode = R.drawable.ic_apps_white_24dp;
                    P1ListFragment p1ListFragment = new P1ListFragment();
                    p1ListFragment.setPictureList(pictureList);
                    p1ListFragment.setFacebook(isFacebook);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (ScreenManager.canBackFragment(this)) {
            btLoginFace.setVisibility(View.VISIBLE);
            ivBack.setVisibility(View.GONE);
            tvLogout.setVisibility(View.VISIBLE);
            ivChangeMode.setVisibility(View.GONE);
            ScreenManager.backFragment(this);
        } else {
            super.onBackPressed();
        }
    }
}
