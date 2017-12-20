package com.pesteam.watchimage.Screen5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pesteam.watchimage.Canvas.CanvasView;
import com.pesteam.watchimage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/18/2017.
 */

public class FragmentScreen52 extends Fragment {

    @BindView(R.id.rcv_screen_52)
    RecyclerView rcv;
    @BindView(R.id.canvas_big_screen_52)
    CanvasView canvas_big;
    @BindView(R.id.img_big_screen_51_52)
    ImageView img_big;
    private Screen5Activity mainActivity;
    private AdapterScreen52 adapter = new AdapterScreen52(this);
    private String url_img;

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_52,container,false);
        ButterKnife.bind(this, view);
        start();
        toolbarButtonAction();
        return view;
    }

    private void toolbarButtonAction() {
        mainActivity.icon_accept.setVisibility(View.VISIBLE);
        mainActivity.icon_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = canvas_big.scaleBitmap(mainActivity.getImage().getWidth(),mainActivity.getImage().getHeight());
                Bitmap bitmap1 = Bitmap.createBitmap(mainActivity.getImage().getWidth(),mainActivity.getImage().getHeight(),Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap1);
                canvas.drawBitmap(mainActivity.getImage(),0,0,null);
                canvas.drawBitmap(bitmap,0,0,null);
                mainActivity.checkPermission(bitmap1);
                mainActivity.finish();
            }
        });
        mainActivity.icon_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frag_activity5, new FragmentScreen5()).addToBackStack(null).commit();
            }
        });
    }


    private void start() {
        Glide.with(this).load(mainActivity.getImg_url()).into(img_big);
        img_big.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                img_big.getViewTreeObserver().removeOnPreDrawListener(this);
                if(mainActivity.getImage().getWidth()>mainActivity.getImage().getHeight()){
                    int height = (int) (mainActivity.getImage().getHeight()/(((float)mainActivity.getImage().getWidth())/img_big.getMeasuredWidth()));
                    Log.e( "onPreDraw: ", mainActivity.getImage().getWidth()+"   "+ mainActivity.getImage().getHeight()+"   "+img_big.getMeasuredWidth()+"   "+height);
                    canvas_big.innit(img_big.getMeasuredWidth(), height, img_big.getMeasuredWidth(),img_big.getMeasuredHeight());
                    Log.e( "onPreDraw: ", img_big.getMeasuredWidth()+"   "+ height);
                } else {
                    Log.e( "onPreDraw: ", mainActivity.getImage().getWidth()+"   "+ mainActivity.getImage().getHeight());
                    int width = (int) (mainActivity.getImage().getWidth()/(((float)mainActivity.getImage().getHeight())/img_big.getMeasuredHeight()));
                    canvas_big.innit(width,img_big.getMeasuredHeight(),img_big.getMeasuredWidth(),img_big.getMeasuredHeight() );
                    Log.e( "onPreDraw: ", width+"   "+ img_big.getMeasuredHeight());
                }
                return true;
            }
        });
        rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Screen5Activity){
            this.mainActivity = (Screen5Activity) context;
        }
    }
}
