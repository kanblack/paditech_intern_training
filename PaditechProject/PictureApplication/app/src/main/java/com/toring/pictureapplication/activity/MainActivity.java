package com.toring.pictureapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.toring.pictureapplication.R;
import com.toring.pictureapplication.network.RetrofitFactory;
import com.toring.pictureapplication.network.modle.MainObject;
import com.toring.pictureapplication.network.service.ServiceGetPicture;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<String> pictureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
    }

    private void getData() {
        ServiceGetPicture getPicture = RetrofitFactory.getInstance().createService(ServiceGetPicture.class);
        getPicture.getPicture()
        .enqueue(new Callback<MainObject>() {
            @Override
            public void onResponse(Call<MainObject> call, Response<MainObject> response) {
                Log.d("main", "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<MainObject> call, Throwable t) {

            }
        });
    }
}
