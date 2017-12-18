package com.toring.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.toring.myapplication.R;
import com.toring.myapplication.network.RetrofitFactory;
import com.toring.myapplication.network.modle.MainObject;
import com.toring.myapplication.network.service.ServiceGetPicture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
