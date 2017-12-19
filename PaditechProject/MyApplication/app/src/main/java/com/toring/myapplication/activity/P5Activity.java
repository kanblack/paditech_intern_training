package com.toring.myapplication.activity;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.toring.myapplication.R;
import com.toring.myapplication.adapter.P5DrawColorAdapter;
import com.toring.myapplication.customvie.DrawingView;
import com.toring.myapplication.fragment.P5DrawFragment;
import com.toring.myapplication.glide.DisplayPicture;
import com.toring.myapplication.manager.ScreenManager;

public class P5Activity extends AppCompatActivity implements View.OnClickListener {
    private String picturePath;
    private DrawingView ivPicture;

    private Toolbar toolbar;
    private RelativeLayout rlOption, rlDraw;

    private View.OnClickListener changeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);

        picturePath = getIntent().getStringExtra(this.getResources().getString(R.string.picture));

        ivPicture = this.findViewById(R.id.iv_picture);
        toolbar = this.findViewById(R.id.toolbar);
        rlOption = this.findViewById(R.id.rl_option);
        rlDraw = this.findViewById(R.id.rl_draw);

        changeColor = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPicture.setColor((Integer) view.getTag());
                View view1 = view.findViewById(R.id.iv_bg);
                ImageView ivColor = view.findViewById(R.id.iv_color);


                GradientDrawable bgShape = (GradientDrawable) view1.getBackground();
                int idColor = P5Activity.this.getResources().getColor(R.color.text_color_primary);
                bgShape.setColor(idColor);

                ViewGroup.LayoutParams params = ivColor.getLayoutParams();
                params.width = params.height - 10;
                params.height = params.width - 10;
                ivColor.setLayoutParams(params);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_draw: {
                P5DrawFragment p5DrawFragment = new P5DrawFragment();
                p5DrawFragment.setOnClickListener(changeColor);
                ScreenManager.replaceFragment(this, R.id.bottom_bar,
                        p5DrawFragment, true);
                break;
            }
            case R.id.rl_option:
                break;
        }
    }
}
