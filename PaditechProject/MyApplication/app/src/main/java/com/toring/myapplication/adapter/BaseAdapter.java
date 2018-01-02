package com.toring.myapplication.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.toring.myapplication.glide.DisplayPicture;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ToRing on 1/2/2018.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.VH> {
    protected Activity context;
    protected List<String> pictureList;
    protected boolean isFacebook;

    public BaseAdapter(Activity context, List<String> pictureList, boolean isFacebook) {
        this.context = context;
        this.pictureList = pictureList;
        this.isFacebook = isFacebook;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public boolean isFacebook() {
        return isFacebook;
    }

    public void setFacebook(boolean facebook) {
        isFacebook = facebook;
    }

    public class VH extends RecyclerView.ViewHolder {
        protected View view;
        protected ImageView ivPicture;

        public VH(View itemView) {
            super(itemView);
        }

        public void bindView(final int position) {
//            if (isFacebook) {
//                String id = pictureList.get(position);
//                Bundle parameter = new Bundle();
//                parameter.putString("fields", "link, images");
//                new GraphRequest(
//                        AccessToken.getCurrentAccessToken(),
//                        "/" + id,
//                        parameter,
//                        HttpMethod.GET,
//                        new GraphRequest.Callback() {
//                            public void onCompleted(GraphResponse response) {
//                                try {
//                                    JSONObject object = (JSONObject) response.getJSONObject().getJSONArray("images").get(0);
//                                    String source = object.getString("source");
//                                    DisplayPicture.displayImageCrop(context, source, ivPicture);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                ).executeAsync();
//            } else {
                DisplayPicture.displayImageCrop(context, pictureList.get(position), ivPicture);
//            }
        }
    }
}
