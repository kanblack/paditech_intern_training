package com.toring.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.toring.myapplication.R;
import com.toring.myapplication.customvie.DrawingView;
import com.toring.myapplication.fragment.P5DrawFragment;
import com.toring.myapplication.fragment.P5ImageFragment;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.manager.ScreenManager;

public class P5Activity extends AppCompatActivity implements View.OnClickListener {
    private String picturePath;
    private DrawingView ivPicture;

    private Toolbar toolbar;
    private RelativeLayout rlOption, rlDraw;

    private View.OnClickListener changeColor;
    private View.OnClickListener changeImage;

    private TextView tvDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);

        picturePath = getIntent().getStringExtra(this.getResources().getString(R.string.picture));

        ivPicture = this.findViewById(R.id.iv_picture);
        toolbar = this.findViewById(R.id.toolbar);
        rlOption = this.findViewById(R.id.rl_option);
        rlDraw = this.findViewById(R.id.rl_draw);

        tvDone = this.findViewById(R.id.tv_done);

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P5Activity.super.onBackPressed();
                ivPicture.setCountPaths(0);
                ivPicture.setCountBitmaps(0);
                ivPicture.setCanDrawImage(false);
                ivPicture.setCanDrawLine(false);
            }
        });

        changeColor = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPicture.setColor((Integer) view.getTag());
            }
        };

        changeImage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPicture.setBitmap((Integer) view.getTag());
            }
        };

        setData();
    }

    private void setData() {
        DisplayPicture.displayImage(this, picturePath, ivPicture);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.background_color));
        toolbar.setTitle(this.getResources().getString(R.string.edit));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P5Activity.this.onBackPressed();
            }
        });

        rlDraw.setOnClickListener(this);
        rlOption.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (ScreenManager.backFragment(P5Activity.this)) {
            ivPicture.setCanDrawLine(false);
            ivPicture.setCanDrawImage(false);
            ivPicture.reset();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_draw: {
                P5DrawFragment p5DrawFragment = new P5DrawFragment();
                p5DrawFragment.setOnClickListener(changeColor);
                ScreenManager.replaceFragment(this, R.id.bottom_bar,
                        p5DrawFragment, true);
                ivPicture.setCanDrawLine(true);
                break;
            }
            case R.id.rl_option:
                P5ImageFragment p5ImageFragment = new P5ImageFragment();
                p5ImageFragment.setOnClickListener(changeImage);
                ScreenManager.replaceFragment(this, R.id.bottom_bar,
                        p5ImageFragment, true);
                ivPicture.setCanDrawImage(true);
                ivPicture.setBitmap(-1);
                break;
        }
    }
}
