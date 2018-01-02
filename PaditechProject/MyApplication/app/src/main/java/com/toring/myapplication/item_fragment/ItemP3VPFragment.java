package com.toring.myapplication.item_fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.toring.myapplication.R;
import com.toring.myapplication.activity.P4Activity;
import com.toring.myapplication.glide.DisplayPicture;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemP3VPFragment extends Fragment {
    private ImageView ivPicture;
    private String picturePath;
    private boolean isFacebook;

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setFacebook(boolean facebook) {
        isFacebook = facebook;
    }

    public ItemP3VPFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_p3_v, container, false);
        ivPicture = view.findViewById(R.id.iv_picture);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemP3VPFragment.this.getContext(), P4Activity.class);
                intent.putExtra(ItemP3VPFragment.this.getContext().getResources().getString(R.string.picture), picturePath);
                intent.putExtra(ItemP3VPFragment.this.getContext().getResources().getString(R.string.is_facebook), isFacebook);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) ItemP3VPFragment.this.getContext(), (View) ivPicture, "detail");
                ItemP3VPFragment.this.getContext().startActivity(intent, options.toBundle());
            }
        });

        if (isFacebook){
            String id = picturePath;
            Bundle parameter = new Bundle();
            parameter.putString("fields", "link, images");
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/" + id,
                    parameter,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            try {
                                JSONObject object = (JSONObject) response.getJSONObject().getJSONArray("images").get(0);
                                String source = object.getString("source");
                                DisplayPicture.displayImageCrop(ItemP3VPFragment.this.getContext(), source, ivPicture);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ).executeAsync();
        }else {
            DisplayPicture.displayImageCrop(this.getContext(), picturePath, ivPicture);
        }
        return view;
    }

}
