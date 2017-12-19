package com.pesteam.watchimage.ScreenMain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pesteam.watchimage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bangindong on 12/13/2017.
 */

public class FragmentScreen1 extends android.support.v4.app.Fragment {

    @BindView(R.id.rcv_screen1)
    RecyclerView rcv_screen1;
    private MainActivity mainActivity;
    private AdapterScreen1 adapter = new AdapterScreen1();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_1,container,false);
        ButterKnife.bind(this, view);
        changeFragment();
        getData();
        return view;
    }

    private void getData() {
                    adapter.setLists(mainActivity.getUrl_image());
                    rcv_screen1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    rcv_screen1.setAdapter(adapter);

    }

    public void changeFragment(){
        mainActivity.bt_change_frag.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frag_screen123, new FragmentScreen2()).addToBackStack(null).commit();
                mainActivity.bt_change_frag.setImageResource(R.drawable.icon_tool_bar_screen2);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof MainActivity){
            this.mainActivity = (MainActivity) context;
        }
    }
}
