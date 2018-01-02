package com.pesteam.watchimage.facebook;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.pesteam.watchimage.ScreenMain.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bangindong on 1/2/2018.
 */

public class GetFbData {
    MainActivity context;

    public GetFbData(Context context) {
        this.context = (MainActivity) context;
    }

    public void getDataAlbumsFromFb(final AccessToken token, final String nextPage ) {
        context.setWhatStyle(MainActivity.STYLE_ALBUMS);
        context.progressBar.setVisibility(View.VISIBLE);
        Log.e( "getDataAlbumsFromFb: ", "daaa");
        GraphRequest request = GraphRequest.newMeRequest(token,
                new GraphRequest.GraphJSONObjectCallback(){

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            Log.e("onCompleted: ", object.toString() );
                            String Name = object.getString("name");
                            context.tex_title_toolbarl.setText(Name.toUpperCase());
                            JSONObject albumsObject = object.getJSONObject("albums");
                            JSONArray dataAlbumArray = albumsObject.getJSONArray("data");
                            context.getAlbums().clear();
                            for (int i = 0; i < dataAlbumArray.length(); i++) {
                                JSONObject dataAlbumObject = (JSONObject) dataAlbumArray.get(i);
                                String url_coverphoto = ((JSONObject)dataAlbumObject.getJSONObject("cover_photo").getJSONArray("images").get(0))
                                        .getString("source");
                                String name = dataAlbumObject.getString("name");
                                int count = dataAlbumObject.getInt("count");
                                String id = dataAlbumObject.getString("id");
                                context.getAlbums().add(new Albums(id,name,url_coverphoto,count));
                            }

                            JSONObject pagingObject = albumsObject.getJSONObject("paging");
                            if(!albumsObject.has("previous")){
                                if(!albumsObject.has("next")){
                                    context.progressBar.setVisibility(View.INVISIBLE);
                                  context.changeDataInFragment();
                                } else {
                                    getDataAlbumsFromFb(token,pagingObject.getJSONObject("cursor").getString("after"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                });

        Bundle parameters = new Bundle();
        if(nextPage.equals("")) {
            parameters.putString("fields", "name,albums.limit(10){cover_photo{images},count,name,id,created_time}");
        } else {
            parameters.putString("fields", "name,albums.limit(10).after("+nextPage+"){cover_photo{images},count,name,id,created_time}");
        }
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void getDataPhotoFromFb(final String id, final AccessToken token, final String nextPage ) {
        context.setWhatStyle(MainActivity.STYLE_PHOTOS);
        context.progressBar.setVisibility(View.VISIBLE);
        final GraphRequest request = GraphRequest.newGraphPathRequest(token,"/"+id,
                new GraphRequest.Callback(){

                    @Override
                    public void onCompleted(GraphResponse response) {
                        try {
                            JSONObject object = response.getJSONObject();
                            JSONObject albumsObject = object.getJSONObject("photos");
                            JSONArray dataAlbumArray = albumsObject.getJSONArray("data");
                            context.getAlbums().clear();
                            for (int i = 0; i < dataAlbumArray.length(); i++) {
                                JSONObject dataAlbumObject = (JSONObject) dataAlbumArray.get(i);
                                JSONArray imagesArray = dataAlbumObject.getJSONArray("images");
                                context.getAlbums().add(new Albums("","",((JSONObject)imagesArray.get(0)).
                                        getString("source"),0));
                            }

                            JSONObject pagingObject = albumsObject.getJSONObject("paging");
                            if(!albumsObject.has("previous")){
                                if(!albumsObject.has("next")){
                                    context.progressBar.setVisibility(View.INVISIBLE);
                                    context.changeDataInFragment();
                                } else {
                                    getDataPhotoFromFb(id,token,pagingObject.getJSONObject("cursor").getString("after"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                });

        Bundle parameters = new Bundle();
        if(nextPage.equals("")) {
            parameters.putString("fields", "photos.limit(20){images}");
        } else {
            parameters.putString("fields", "photos.limit(20).after("+nextPage+"){images}");
        }
        request.setParameters(parameters);
        request.executeAsync();
    }
}
