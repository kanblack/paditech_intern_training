package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.P5Adapter;
import com.toring.paditechproject.model.P5Message;
import com.toring.paditechproject.model.P5Person;

import java.util.ArrayList;
import java.util.List;

public class P5Activity extends AppCompatActivity {

    private RecyclerView rv;

    private P5Adapter p5Adapter;
    private List<P5Person> p5PersonList;
    private List<P5Message> p5MessageList;
    private List all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);
        rv = this.findViewById(R.id.rv_p5);

        p5PersonList = new ArrayList<>();
        p5PersonList.add(new P5Person("Trinh", true, false, R.drawable.iv_car_hospital, R.drawable.iv_repair_car));
        p5PersonList.add(new P5Person("Nam", false, false, R.drawable.iv_model_and_car, R.drawable.iv_nice_car));
        p5PersonList.add(new P5Person("Quân", false, false, R.drawable.iv_car_hospital, R.drawable.iv_model_and_car));
        p5PersonList.add(new P5Person("Huy", true, true, R.drawable.iv_nice_car, R.drawable.iv_model_and_car));
        p5PersonList.add(new P5Person("Long", false, false, R.drawable.iv_repair_car, R.drawable.iv_car_hospital));
        p5PersonList.add(new P5Person("Ma", false, true, R.drawable.iv_car_hospital, R.drawable.iv_model_and_car));
        p5PersonList.add(new P5Person("Ghost", true, false, R.drawable.iv_car_hospital, R.drawable.iv_repair_car));

        p5MessageList = new ArrayList<>();
        p5MessageList.add(new P5Message(false, R.drawable.iv_car_hospital, "Nam", "You: Ăn cơm chưa", true, true, false, "now"));
        p5MessageList.add(new P5Message(false, R.drawable.iv_model_and_car, "Just You", "You sent a file", true, true, false, "10:23"));
        p5MessageList.add(new P5Message(true, R.drawable.iv_nice_car, "Hoàng'ss", "Yebbbbbbbbbbbb", false, true, false, "12:23"));
        p5MessageList.add(new P5Message(true, R.drawable.iv_nice_car, "Hoàng'ss", "Yebbbbbbbbbbbb", false, true, false, "12:23"));
        p5MessageList.add(new P5Message(false, R.drawable.iv_repair_car, "Oh my chuối", "Nay đi đâu đấy", false, true, false, "21:23"));
        p5MessageList.add(new P5Message(false, R.drawable.iv_nice_car, "Là tên", "Nay kiểm tra nhé !!!", true, false, false, "Sun"));
        p5MessageList.add(new P5Message(false, R.drawable.iv_nice_car, "Là tên", "Nay kiểm tra nhé !!!", true, false, false, "Sun"));
        p5MessageList.add(new P5Message(false, R.drawable.iv_nice_car, "Là tên", "Nay kiểm tra nhé !!!", true, false, false, "Sun"));
        p5MessageList.add(new P5Message(false, R.drawable.iv_nice_car, "Là tên", "Nay kiểm tra nhé !!!", true, false, false, "Sun"));
        p5MessageList.add(new P5Message(true, R.drawable.iv_car_hospital, "No name", "Ăn cơm chưa", true, true, false, "23 tháng 6"));
        p5MessageList.add(new P5Message(true, R.drawable.iv_car_hospital, "No name", "Ăn cơm chưa", true, true, false, "23 tháng 6"));
        p5MessageList.add(new P5Message(false, R.drawable.iv_repair_car, "Nam", "Ăn cơm chưa", true, true, false, "12/10/2017"));

        all = new ArrayList();
        all.add(p5PersonList);
        all.addAll(p5MessageList);

        p5Adapter = new P5Adapter(this, all);
        rv.setAdapter(p5Adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }
}
