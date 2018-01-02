package com.pesteam.watchimage.Screen5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pesteam.watchimage.R;
import com.pesteam.watchimage.ScreenMain.AdapterScreen3;
import com.pesteam.watchimage.ScreenMain.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Screen5Activity extends AppCompatActivity {

    @BindView(R.id.icon_back_img_screen5)
    ImageButton icon_back_img;
    @BindView(R.id.icon_accept_screen5)
    ImageButton icon_accept;
    private android.support.v4.app.FragmentManager fm;
    private FragmentTransaction ft_tran;
    private String img_url;
    private int position;
    private Boolean exit = false;
    private int wwhatFrag;
    private Canvas mcanvas;
    private Bitmap bitmap;
    public static final int FRAG_5 = 0;
    public static final int FRAG_51 = 1;
    public static final int FRAG_52 = 2;

    public int getPosition() {
        return position;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setWwhatFrag(int wwhatFrag) {
        this.wwhatFrag = wwhatFrag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);
        ButterKnife.bind(this);
        getData();
        start();
        changefragment();
    }

    private void changefragment() {
        ft_tran.replace(R.id.frag_activity5, new FragmentScreen5());
        ft_tran.addToBackStack(null);
        ft_tran.commit();
        wwhatFrag = FRAG_5;
    }


    private void getData() {
            Intent intent = getIntent();
            img_url = intent.getStringExtra("img_url");
            position = intent.getIntExtra("position",0);
    }

    @SuppressLint("CommitTransaction")
    private void start() {
        fm = getSupportFragmentManager();
        ft_tran = fm.beginTransaction();

    }

    @Override
    public void onBackPressed() {
        switch (wwhatFrag){
            case FRAG_5:
                finish();
                break;
        }

    }

    public void drawBitmap(Bitmap mbitmap){
        Log.e( "drawBitmap: ", mbitmap.getWidth() + "   "+ mbitmap.getHeight() );
        Bitmap bitmap1 = Bitmap.createScaledBitmap(mbitmap, bitmap.getWidth(),bitmap.getHeight(),true);
        mcanvas.drawBitmap(bitmap1,0,0,null);
    }

    public void creatBitMap(final int width, final int height) {

            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            mcanvas = new Canvas(bitmap);
            Log.e("creatBitMap: ", width+"   "+height);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
