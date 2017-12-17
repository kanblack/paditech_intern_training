package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.P1Adapter;
import com.toring.paditechproject.network.model.P1Data;
import com.toring.paditechproject.network.model.P1MainObject;
import com.toring.paditechproject.network.service.ServiceGetData;
import com.toring.paditechproject.network.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class P1Activity extends AppCompatActivity {
    private RecyclerView rvP1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1);

        rvP1 = this.findViewById(R.id.rv_p1);

        getData();
    }

    public void getData() {
        ServiceGetData serviceGetData = RetrofitFactory.getInstance().createServiceClass(ServiceGetData.class);
        serviceGetData.getData().enqueue(new Callback<P1MainObject>() {
            @Override
            public void onResponse(Call<P1MainObject> call, Response<P1MainObject> response) {
                P1MainObject p1MainObject = response.body();
                P1Data data = p1MainObject.getP1Data();

                P1Adapter adapter = new P1Adapter(P1Activity.this, data.getP1SectionList());
                rvP1.setAdapter(adapter);
                rvP1.setLayoutManager(new LinearLayoutManager(P1Activity.this));
            }

            @Override
            public void onFailure(Call<P1MainObject> call, Throwable t) {

            }
        });
    }
}
