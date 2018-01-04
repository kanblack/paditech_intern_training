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
import com.toring.myapplication.glide.GlideApp;
import com.toring.myapplication.network.facebook_model.Album;

import org.json.JSONException;

import java.util.List;

/**
 * Created by ToRing on 12/31/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumVH> {
    private Context context;
    private List<Album> albumList;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

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
        private View view;

        public AlbumVH(View itemView) {
            super(itemView);

            ivCover = itemView.findViewById(R.id.iv_cover);
            tvName = itemView.findViewById(R.id.tv_album_name);
            tvCount = itemView.findViewById(R.id.tv_count);
            view = itemView;
        }

        public void onBindView(int position) {
            view.setTag(albumList.get(position));
            view.setOnClickListener(onClickListener);
            Album album = albumList.get(position);

            tvName.setText(album.getName());
            tvCount.setText(album.getPhotoCount() + " Photos");
            if (album.getPhotoCount() == 0){
                DisplayPicture.displayImageCrop(context, R.drawable.ic_image_black_24dp, ivCover);
            }else{
                DisplayPicture.displayImageCrop(context,
                        album.getUrl(),
                        ivCover);
            }
        }
    }
}
