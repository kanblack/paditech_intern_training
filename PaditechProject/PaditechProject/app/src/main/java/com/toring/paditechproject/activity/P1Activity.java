package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.P1Adapter;
import com.toring.paditechproject.network.model.P1Data;
import com.toring.paditechproject.network.model.P1MainObject;
import com.toring.paditechproject.network.service.GetData;
import com.toring.paditechproject.network.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class P1Activity extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1);

        rv = this.findViewById(R.id.rv);

        getData();
    }

    public void getData() {
        GetData getData = RetrofitFactory.getInstance().createServiceClass(GetData.class);
        getData.getData().enqueue(new Callback<P1MainObject>() {
            @Override
            public void onResponse(Call<P1MainObject> call, Response<P1MainObject> response) {
                Log.d("onre", "onResponse: " + response.body());
                P1MainObject p1MainObject = response.body();
               P1Data data = p1MainObject.getP1Data();

                P1Adapter adapter = new P1Adapter(P1Activity.this, data.getP1Section());
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(P1Activity.this));
            }

            @Override
            public void onFailure(Call<P1MainObject> call, Throwable t) {

            }
        });
    }
}
