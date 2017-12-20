package com.pesteam.watchimage.ScreenMain;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.pesteam.watchimage.R;
import com.pesteam.watchimage.Screen4Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/13/2017.
 */

public class FragmentScreen2 extends android.support.v4.app.Fragment {
    private MainActivity mainActivity;
    @BindView(R.id.grid_screen2)
    GridView grd_view;
    @BindView(R.id.progress)
    ProgressBar progress;
    private AdapterScreen2 adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_2, container, false);
        ButterKnife.bind(this, view);
        changeFragment();
        getData();
        return view;
    }

    private void getData() {

        adapter.setList(mainActivity.getUrl_image());
        grd_view.setAdapter(adapter);
        grd_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), Screen4Activity.class);
                intent.putExtra("img_url", mainActivity.getUrl_image().get(i));
                intent.putExtra("position", i);
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) getContext(),
                                view,
                                ViewCompat.getTransitionName(view));
                mainActivity.startActivity(intent, optionsCompat.toBundle());
            }
        });
    }

    public void changeFragment() {
        adapter = new AdapterScreen2(this);
        mainActivity.bt_change_frag.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frag_screen123, new FragmentScreen3()).addToBackStack(null).commit();
                mainActivity.bt_change_frag.setImageResource(R.drawable.icon_tool_bar_screen3);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }
}
