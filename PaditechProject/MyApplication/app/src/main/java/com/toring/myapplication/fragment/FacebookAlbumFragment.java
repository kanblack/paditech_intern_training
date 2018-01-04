package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.toring.myapplication.R;
import com.toring.myapplication.activity.MainActivity;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.manager.ScreenManager;
import com.toring.myapplication.network.facebook_model.Album;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.facebook.AccessToken.getCurrentAccessToken;

public class FacebookAlbumFragment extends Fragment {
    private ImageView ivBack;
    private TextView tvLogout;
    private TextView tvTitle;

    public FacebookAlbumFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebook_album, container, false);

        ivBack = view.findViewById(R.id.iv_back);
        tvLogout = view.findViewById(R.id.tv_logout);
        tvTitle = view.findViewById(R.id.tv_title);

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) FacebookAlbumFragment.this.getActivity()).logoutFacebook();
            }
        });

        getProfile();
        getListAlbum();

        return view;
    }

    private void getProfile() {
        GraphRequest request = GraphRequest.newGraphPathRequest(
                getCurrentAccessToken(),
                "/" + getCurrentAccessToken().getUserId(),
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        DisplayPicture.displayImageCircleCrop(FacebookAlbumFragment.this.getActivity(),
                                Profile.getCurrentProfile().getProfilePictureUri(500, 500).toString(),
                                ivBack);
                        tvTitle.setText(Profile.getCurrentProfile().getName());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "profile_pic");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void getListAlbum() {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "albums.limit(1000){picture{url},name,photo_count}");
        GraphRequest request = new GraphRequest(
                getCurrentAccessToken(),
                "/" + getCurrentAccessToken().getUserId(),
                parameters,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            ArrayList<Album> arrayList = new ArrayList<>();
                            JSONArray albums = response.getJSONObject().getJSONObject("albums").getJSONArray("data");
                            for (int j = 0; j < albums.length(); j++) {
                                JSONObject object = (JSONObject) albums.get(j);
                                Album album = new Album();
                                album.setId(object.getString("id"));
                                album.setName(object.getString("name"));
                                album.setPhotoCount(object.getInt("photo_count"));
                                album.setUrl(object.getJSONObject("picture").getJSONObject("data").getString("url"));
                                arrayList.add(album);
                            }
                            AlbumListFragment albumListFragment = new AlbumListFragment();
                            albumListFragment.setAlbumList(arrayList);
                            albumListFragment.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    NoFacebookFragment noFacebookFragment = new NoFacebookFragment();
                                    noFacebookFragment.setAlbumID(((Album)view.getTag()).getId());
                                    ScreenManager.replaceFragment((MainActivity)FacebookAlbumFragment.this.getActivity(), R.id.full, noFacebookFragment, true);
                                }
                            });
                            ScreenManager.replaceFragment((MainActivity)FacebookAlbumFragment.this.getActivity(), R.id.content,
                                    albumListFragment, false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        request.executeAsync();
    }
}
