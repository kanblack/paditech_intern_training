package com.toring.myapplication.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.toring.myapplication.R;
import com.toring.myapplication.activity.MainActivity;
import com.toring.myapplication.manager.ScreenManager;
import com.toring.myapplication.network.RetrofitFactory;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class NoFacebookFragment extends Fragment {
    private ImageView ivChangeMode;
    private ImageView btLoginFace;
    private ImageView ivBack;

    private String albumID = null;
    private String afterCursor;

    private List<String> pictureList;

    private FragmentBase currentFragment;

    private int modeVIew = 0;
    private int iconChangeMode = R.drawable.ic_apps_white_24dp;

    public NoFacebookFragment() {
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_facebook, container, false);

        ivChangeMode = view.findViewById(R.id.iv_change_mode);
        btLoginFace = view.findViewById(R.id.bt_login_face);
        ivBack = view.findViewById(R.id.iv_back);

        btLoginFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getCurrentAccessToken() == null) {
                    loginFacebook();
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenManager.backFragment((MainActivity) NoFacebookFragment.this.getActivity());
            }
        });

        ivChangeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMode();
            }
        });

        pictureList = new ArrayList<>();
        if (albumID == null) {
            btLoginFace.setVisibility(View.VISIBLE);
            ivBack.setVisibility(View.GONE);
            getDataNotLogin();
        } else {
            btLoginFace.setVisibility(View.GONE);
            ivBack.setVisibility(View.VISIBLE);
            getAlbumPhoto(albumID);
        }

        return view;
    }


    private void loginFacebook() {
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(
//                "public_profile", "user_photos", "user_friends"));
        ((MainActivity) this.getActivity()).loginFacebook();
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
            currentFragment = p1ListFragment;
            currentFragment.setPictureList(pictureList);
            currentFragment.setNoFacebookFragment(this);
            currentFragment.setAlbum(albumID);
            ScreenManager.replaceFragment((MainActivity) this.getActivity(),
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
                            currentFragment = p1ListFragment;
                            currentFragment.setPictureList(pictureList);
                            currentFragment.setNoFacebookFragment(NoFacebookFragment.this);
                            currentFragment.setAlbum(albumID);
                            ScreenManager.replaceFragment((MainActivity) NoFacebookFragment.this.getActivity(),
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
                            final AlertDialog.Builder alertDialog =
                                    new AlertDialog.Builder(
                                            NoFacebookFragment.this.getActivity(),
                                            R.style.dialog);
                            alertDialog.setMessage("Kết nối thất bại. Vui lòng kiểm tra lại kết nối internet.");
                            alertDialog.setPositiveButton("Tải lại", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    getDataNotLogin();
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

    private void getAlbumPhoto(final String albumID) {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "width");
        parameters.putString("limit", "10");

        new GraphRequest(
                getCurrentAccessToken(),
                "/" + albumID + "/photos",
                parameters,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            pictureList = new ArrayList<>();
                            JSONArray temp = response.getJSONObject().getJSONArray("data");
                            for (int i = 0; i < temp.length(); i++) {
                                JSONObject obj = (JSONObject) temp.get(i);
                                int width = obj.getInt("width");
                                pictureList.add(getImageUrlById(((JSONObject) temp.get(i)).getString("id"), width));
                            }
                            P1ListFragment p1ListFragment = new P1ListFragment();
                            currentFragment = p1ListFragment;
                            currentFragment.setPictureList(pictureList);
                            currentFragment.setNoFacebookFragment(NoFacebookFragment.this);
                            currentFragment.setAlbum(albumID);
                            ScreenManager.replaceFragment((MainActivity) NoFacebookFragment.this.getActivity(),
                                    R.id.content,
                                    currentFragment, false);

                            afterCursor = response.getJSONObject().getJSONObject("paging").getJSONObject("cursors").getString("after");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    public void loadMorePhoto() {
        if (afterCursor != null) {
            GraphRequest request = GraphRequest.newGraphPathRequest(
                    getCurrentAccessToken(),
                    "/" + albumID + "/photos",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            try {
                                JSONArray temp = response.getJSONObject().getJSONArray("data");
                                for (int i = 0; i < temp.length(); i++) {
                                    JSONObject obj = (JSONObject) temp.get(i);
                                    int width = obj.getInt("width");
                                    pictureList.add(getImageUrlById(((JSONObject) temp.get(i)).getString("id"), width));
                                }
                                afterCursor = response.getJSONObject().getJSONObject("paging").getJSONObject("cursors").getString("after");
                                currentFragment.loadMore();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                afterCursor = null;
                            }
                        }
                    });

            Bundle parameters = new Bundle();
            parameters.putString("pretty", "0");
            parameters.putString("fields", "height,width");
            parameters.putString("limit", "10");
            parameters.putString("after", afterCursor);
            request.setParameters(parameters);
            request.executeAsync();
        }
    }

    public static String getImageUrlById(String id, int expectWidth) {
        if (AccessToken.getCurrentAccessToken() != null) {
            String accessToken = getCurrentAccessToken().getToken();
            return String.format("https://graph.facebook.com/%s/picture?width=%d&access_token=%s", id, expectWidth, accessToken);
        }
        return null;
    }

    private void changeMode() {
        if (pictureList != null) {
            switch (modeVIew) {
                case 0: {
                    iconChangeMode = R.drawable.ic_slideshow_white_24dp;
                    P2GridFragment p2GridFragment = new P2GridFragment();
                    currentFragment = p2GridFragment;
                    break;
                }

                case 1: {
                    iconChangeMode = R.drawable.ic_view_list_white_24dp;
                    P3SlideFragment p3SlideFragment = new P3SlideFragment();
                    currentFragment = p3SlideFragment;
                    break;
                }

                case 2: {
                    iconChangeMode = R.drawable.ic_apps_white_24dp;
                    P1ListFragment p1ListFragment = new P1ListFragment();
                    currentFragment = p1ListFragment;
                    modeVIew = -1;
                    break;
                }
            }

            ivChangeMode.setImageResource(iconChangeMode);

            currentFragment.setNoFacebookFragment(this);
            currentFragment.setPictureList(pictureList);
            currentFragment.setAlbum(albumID);
            ScreenManager.replaceFragment((MainActivity) NoFacebookFragment.this.getActivity(),
                    R.id.content,
                    currentFragment,
                    false);

            modeVIew++;
        }
    }
}
