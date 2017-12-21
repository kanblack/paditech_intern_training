package com.pesteam.watchimage.Screen5;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.pesteam.watchimage.Canvas.CanvasView;
import com.pesteam.watchimage.Canvas.EditableImageView;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.saveImage.SaveImage;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/18/2017.
 */

public class FragmentScreen52 extends Fragment {

    @BindView(R.id.rcv_screen_52)
    RecyclerView rcv;
    @BindView(R.id.editable_img_screen52)
    EditableImageView editable_img_screen52;
    private Screen5Activity mainActivity;
    private AdapterScreen52 adapter = new AdapterScreen52(this);
    private SaveImage saveImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_52,container,false);
        ButterKnife.bind(this, view);
        start();
        return view;
    }



    private void start() {
        saveImage = new SaveImage(this.getContext(), mainActivity.getImg_url(), mainActivity.getPosition(), SaveImage.SCR_52);
        editable_img_screen52.setImage(mainActivity.getImg_url());
        rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv.setAdapter(adapter);
        toolbarButtonAction();
    }

    private void toolbarButtonAction() {
        mainActivity.icon_accept.setVisibility(View.VISIBLE);
        mainActivity.icon_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage.loadImage(editable_img_screen52.canvasView.getMbitmap());
            }
        });
        mainActivity.icon_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frag_activity5, new FragmentScreen5()).addToBackStack(null).commit();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Screen5Activity){
            this.mainActivity = (Screen5Activity) context;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e( "onRequestPermission: ", ",,,,," );
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("Permission: ",  permissions[0] + "was " + grantResults[0]);
            saveImage.saveToInternalStorage(saveImage.getImage(),saveImage.getPosition());
        }
        else {
            Toast.makeText(this.getContext(),"Bạn không cho lưu vào thẻ nhớ, không thể lưu được",Toast.LENGTH_LONG).show();
        }
    }
}
