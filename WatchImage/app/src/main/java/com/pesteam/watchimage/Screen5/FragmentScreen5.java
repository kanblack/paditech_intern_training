package com.pesteam.watchimage.Screen5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pesteam.watchimage.Canvas.EditableImageView;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.saveImage.SaveImage;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/18/2017.
 */

public class FragmentScreen5 extends Fragment {

    private Screen5Activity mainActivity;
    private SaveImage saveImage;
    @BindView(R.id.editable_img_screen5)
    EditableImageView editable_img_screen5;
    @BindView(R.id.bt_draw_screen5)
    Button bt_draw;
    @BindView(R.id.bt_frame_screen5)
    Button bt_frame;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_5, container, false);
        ButterKnife.bind(this, view);
        start();
        changeFragment();
        return view;
    }

    private void start() {
        if (mainActivity.getBitmap() != null) {
            Log.e("start: ", mainActivity.getBitmap().getWidth() + "   " + mainActivity.getBitmap().getHeight());
        }

        editable_img_screen5.setImage(mainActivity.getImg_url());
        if (mainActivity.getBitmap() == null) {
            final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (editable_img_screen5.canvasView.width == 0) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mainActivity.creatBitMap(editable_img_screen5.canvasView.width, editable_img_screen5.canvasView.height);
                }
            });
            thread.start();
        } else {

        }
    }


    public void changeFragment() {
        mainActivity.icon_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.finish();
            }
        });
        mainActivity.icon_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mainActivity.getBitmap()!= null) {
                    saveImage = new SaveImage(mainActivity, mainActivity.getImg_url(), mainActivity.getPosition(), SaveImage.FRAME);
                    saveImage.loadImage(mainActivity.getBitmap());
                } else {
                    saveImage = new SaveImage(mainActivity, mainActivity.getImg_url(), mainActivity.getPosition(), SaveImage.NO_FRAME);
                    saveImage.loadImage(null);
                }
            }
        });
        bt_frame.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View view) {
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frag_activity5, new FragmentScreen51()).addToBackStack(null).commit();
                mainActivity.setWwhatFrag(Screen5Activity.FRAG_51);
            }
        });
        bt_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frag_activity5, new FragmentScreen52()).addToBackStack(null).commit();
                mainActivity.setWwhatFrag(Screen5Activity.FRAG_52);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Screen5Activity) {
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
