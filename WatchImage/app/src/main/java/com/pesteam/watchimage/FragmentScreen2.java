package com.pesteam.watchimage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.pesteam.watchimage.getData.APIService;
import com.pesteam.watchimage.getData.APIUtils;
import com.pesteam.watchimage.getData.ChildScreen1Class;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bangindong on 12/13/2017.
 */

public class FragmentScreen2 extends android.support.v4.app.Fragment {
    private MainActivity mainActivity;
    @BindView(R.id.grid_screen2)
    GridView grd_view;
    private APIService mApiService = APIUtils.getAPIService();
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
        mApiService.loadData().enqueue(new Callback<ChildScreen1Class>() {
            @Override
            public void onResponse(Call<ChildScreen1Class> call, final Response<ChildScreen1Class> response) {
                if(response.isSuccessful()){
                    adapter.setList(response.body().getData());
                    grd_view.setAdapter(adapter);
                    grd_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(mainActivity,Screen4Activity.class);
                            intent.putExtra("img_url",response.body().getData().get(i));
                            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation( mainActivity,
                                            adapter.getImageView(),
                                            ViewCompat.getTransitionName(adapter.getImageView()));
                            mainActivity.startActivity(intent, optionsCompat.toBundle());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ChildScreen1Class> call, Throwable t) {

            }
        });
    }

    public void changeFragment(){
        adapter = new AdapterScreen2(mainActivity);
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

        if(context instanceof MainActivity){
    }               this.mainActivity = (MainActivity) context;
}
}
