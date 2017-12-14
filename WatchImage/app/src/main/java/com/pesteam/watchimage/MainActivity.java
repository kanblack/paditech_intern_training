package com.pesteam.watchimage;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private android.support.v4.app.FragmentManager fm;
    private FragmentTransaction ft_tran;
    @BindView(R.id.bt_change_fragment_screen1)
    ImageButton bt_change_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        start();
    }

    public void start() {
        fm = getSupportFragmentManager();
        ft_tran = fm.beginTransaction();
        bt_change_frag.setImageResource(R.drawable.icon_tool_bar_screen1);
        ft_tran.replace(R.id.frag_screen123, new FragmentScreen1());
        ft_tran.addToBackStack(null);
        ft_tran.commit();
    }


}
