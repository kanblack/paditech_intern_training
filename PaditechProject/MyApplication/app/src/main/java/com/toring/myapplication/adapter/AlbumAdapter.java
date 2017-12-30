package com.toring.myapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.network.facebook_model.Album;

import org.json.JSONException;

import java.util.List;

/**
 * Created by ToRing on 12/31/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumVH> {
    private Context context;
    private List<Album> albumList;

    public AlbumAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public AlbumVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_album, parent, false);
        return new AlbumVH(view);
    }

    @Override
    public void onBindViewHolder(AlbumVH holder, int position) {
        holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class AlbumVH extends RecyclerView.ViewHolder {
        private ImageView ivCover;
        private TextView tvName, tvCount;

        public AlbumVH(View itemView) {
            super(itemView);

            ivCover = itemView.findViewById(R.id.iv_cover);
            tvName = itemView.findViewById(R.id.tv_album_name);
            tvCount = itemView.findViewById(R.id.tv_count);
        }

        public void onBindView(int position) {
            tvName.setText(albumList.get(position).getName());
            Bundle bundle = new Bundle();
            bundle.putString("fields", "id, count, cover_photo, name");
            GraphRequest request = new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/" + albumList.get(position).getId(),
                    bundle,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            try {
                                tvCount.setText(response.getJSONObject().getString("count") + "  photos");

                                Bundle bundle = new Bundle();
                                bundle.putString("fields", "picture");
                                new GraphRequest(
                                        AccessToken.getCurrentAccessToken(),
                                        "/" + response.getJSONObject().getJSONObject("cover_photo").getString("id"),
                                        bundle,
                                        HttpMethod.GET,
                                        new GraphRequest.Callback() {
                                            public void onCompleted(GraphResponse response) {
                                                try {
                                                    DisplayPicture.displayImageCrop(context,
                                                            response.getJSONObject().getString("picture"),
                                                            ivCover);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                ).executeAsync();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            request.executeAsync();
        }
    }
}
