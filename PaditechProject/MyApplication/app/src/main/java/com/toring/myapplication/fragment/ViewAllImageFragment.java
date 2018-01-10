package com.toring.myapplication.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.toring.myapplication.R;
import com.toring.myapplication.activity.MainActivity;
import com.toring.myapplication.manager.ScreenManager;
import com.toring.myapplication.network.RetrofitFactory;
import com.toring.myapplication.network.image_object.ImageObject;
import com.toring.myapplication.network.no_face_modle.DataObject;
import com.toring.myapplication.network.no_face_modle.MainObject;
import com.toring.myapplication.network.service.ServiceGetPicture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.AccessToken.getCurrentAccessToken;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAllImageFragment extends Fragment {
    private ImageView ivChangeMode;
    private ImageView btLoginFace;
    private ImageView ivBack;
    private TextView tvTitle;

    private String albumID = null;
    private String afterCursor;
    private String title;

    private List<ImageObject> imageObjectList;

    private FragmentBase currentFragment;

    private int modeView = 0;
    private int iconChangeMode = R.drawable.ic_apps_white_24dp;

    public ViewAllImageFragment() {
    }

    public void setTitle(String title) {
        this.title = title;
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
        tvTitle = view.findViewById(R.id.tv_title);

        if (title != null) {
            tvTitle.setText(title);
        }

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
                ScreenManager.backFragment((MainActivity) ViewAllImageFragment.this.getActivity());
            }
        });

        ivChangeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeMode();
            }
        });

        imageObjectList = new ArrayList<>();
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
            imageObjectList = new ArrayList<>();
            for (DataObject dataObject : myData) {
                imageObjectList.add(new ImageObject(dataObject.getUrl()));
            }
            P1ListFragment p1ListFragment = new P1ListFragment();
            currentFragment = p1ListFragment;
            currentFragment.setImageObjectList(imageObjectList);
            currentFragment.setViewAllImageFragment(this);
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
                            MainObject mainObject = response.body();
                            List<String> listURL = mainObject.getData();
                            for (String s : listURL) {
                                imageObjectList.add(new ImageObject(s));
                            }

                            P1ListFragment p1ListFragment = new P1ListFragment();
                            currentFragment = p1ListFragment;
                            currentFragment.setImageObjectList(imageObjectList);
                            currentFragment.setViewAllImageFragment(ViewAllImageFragment.this);
                            currentFragment.setAlbum(albumID);
                            ScreenManager.replaceFragment((MainActivity) ViewAllImageFragment.this.getActivity(),
                                    R.id.content,
                                    currentFragment,
                                    false);

                            realm.beginTransaction();
                            for (ImageObject imageObject : imageObjectList) {
                                realm.copyToRealm(imageObject);
                            }
                            realm.commitTransaction();
                            realm.close();
                        }

                        @Override
                        public void onFailure(Call<MainObject> call, Throwable t) {
                            final AlertDialog.Builder alertDialog =
                                    new AlertDialog.Builder(
                                            ViewAllImageFragment.this.getActivity(),
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
        parameters.putString("fields", "width,height, name");
        parameters.putString("limit", "10");

        new GraphRequest(
                getCurrentAccessToken(),
                "/" + albumID + "/photos",
                parameters,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            imageObjectList = new ArrayList<>();
                            JSONArray temp = response.getJSONObject().getJSONArray("data");
                            for (int i = 0; i < temp.length(); i++) {
                                JSONObject obj = (JSONObject) temp.get(i);
                                int width = obj.getInt("width");

                                String name =  null;
                                if (obj.has("name")) {
                                    name = obj.getString("name");
                                }
                                ImageObject imageObject = new ImageObject(name,
                                        getImageUrlById(obj.getString("id"), width),
                                        width,
                                        obj.getInt("height"));
                                imageObjectList.add(imageObject);
                            }
                            P1ListFragment p1ListFragment = new P1ListFragment();
                            currentFragment = p1ListFragment;
                            currentFragment.setImageObjectList(imageObjectList);
                            currentFragment.setViewAllImageFragment(ViewAllImageFragment.this);
                            currentFragment.setAlbum(albumID);
                            ScreenManager.replaceFragment((MainActivity) ViewAllImageFragment.this.getActivity(),
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
                                int start = imageObjectList.size();
                                JSONArray temp = response.getJSONObject().getJSONArray("data");
                                for (int i = 0; i < temp.length(); i++) {
                                    JSONObject obj = (JSONObject) temp.get(i);
                                    int width = obj.getInt("width");

                                    String name =  null;
                                    if (obj.has("name")) {
                                        name = obj.getString("name");
                                    }
                                    ImageObject imageObject = new ImageObject(name,
                                            getImageUrlById(obj.getString("id"), width),
                                            width,
                                            obj.getInt("height"));
                                    imageObjectList.add(imageObject);
                                }
                                afterCursor = response.getJSONObject().getJSONObject("paging").getJSONObject("cursors").getString("after");
                                currentFragment.loadMore(start, imageObjectList.size());
                            } catch (JSONException e) {
                                e.printStackTrace();
                                afterCursor = null;
                            }
                        }
                    });

            Bundle parameters = new Bundle();
            parameters.putString("pretty", "0");
            parameters.putString("fields", "width,height, name");
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
        if (imageObjectList != null) {
            switch (modeView) {
                case 0: {
                    iconChangeMode = R.drawable.ic_slideshow_white_24dp;
                    P2GridFragment p2GridFragment = new P2GridFragment();
                    currentFragment = p2GridFragment;
                    break;
                }

                case 1: {
                    iconChangeMode = R.drawable.ic_dashboard_white_24dp;
                    P3SlideFragment p3SlideFragment = new P3SlideFragment();
                    currentFragment = p3SlideFragment;
                    break;
                }

                case 2: {
                    iconChangeMode = R.drawable.ic_view_list_white_24dp;
                    P4StaggeredGridFragment p4StaggeredGridFragment = new P4StaggeredGridFragment();
                    currentFragment = p4StaggeredGridFragment;
                    break;
                }

                case 3: {
                    iconChangeMode = R.drawable.ic_apps_white_24dp;
                    P1ListFragment p1ListFragment = new P1ListFragment();
                    currentFragment = p1ListFragment;
                    modeView = -1;
                    break;
                }
            }

            ivChangeMode.setImageResource(iconChangeMode);

            currentFragment.setViewAllImageFragment(this);
            currentFragment.setImageObjectList(imageObjectList);
            currentFragment.setAlbum(albumID);
            ScreenManager.replaceFragment((MainActivity) ViewAllImageFragment.this.getActivity(),
                    R.id.content,
                    currentFragment,
                    false);

            modeView++;
        }
    }

    private void createImageObject() {

    }
}
