package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.network.model.P1MainObject;
import com.toring.paditechproject.network.service.GetData;
import com.toring.paditechproject.network.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class P1Activity extends AppCompatActivity {
    private ImageView ivHospital, ivNhaThuoc, ivBenh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1);

        getData();
    }

    public void getData() {
        GetData getData = RetrofitFactory.getInstance().createServiceClass(GetData.class);
        getData.getData().enqueue(new Callback<P1MainObject>() {
            @Override
            public void onResponse(Call<P1MainObject> call, Response<P1MainObject> response) {
                Log.d("onre", "onResponse: " + response.body());
                P1MainObject p1MainObject = response.body();
                 p1MainObject.getP1Data();
            }

            @Override
            public void onFailure(Call<P1MainObject> call, Throwable t) {

            }
        });
    }
}
