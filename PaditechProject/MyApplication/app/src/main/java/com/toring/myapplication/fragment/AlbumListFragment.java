package com.toring.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toring.myapplication.R;
import com.toring.myapplication.adapter.AlbumAdapter;
import com.toring.myapplication.network.facebook_model.Album;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumListFragment extends Fragment {

    private RecyclerView rvAlbum;
    private ArrayList<Album> albumList;
    private AlbumAdapter albumAdapter;

    public void setAlbumList(ArrayList<Album> albumList) {
        this.albumList = albumList;
    }

    public AlbumListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album_list, container, false);
        rvAlbum = view.findViewById(R.id.rv_album);
        albumAdapter = new AlbumAdapter(this.getContext(), albumList);
        rvAlbum.setAdapter(albumAdapter);
        rvAlbum.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

}
