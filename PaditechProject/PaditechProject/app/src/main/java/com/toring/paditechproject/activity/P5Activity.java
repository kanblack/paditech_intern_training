package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.FirstP5Adapter;
import com.toring.paditechproject.adapter.SecondP5Adapter;
import com.toring.paditechproject.model.Message;
import com.toring.paditechproject.model.Person;

import java.util.ArrayList;
import java.util.List;

public class P5Activity extends AppCompatActivity {

    private RecyclerView rvFirst;
    private RecyclerView rvSeconds;

    private FirstP5Adapter firstP5Adapter;
    private List<Person> personList;


    private SecondP5Adapter secondP5Adapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);

        rvFirst =  this.findViewById(R.id.rv_first);
        rvSeconds =  this.findViewById(R.id.rv_seconds);

        personList = new ArrayList<>();
        personList.add(new Person("Trinh", true, false, R.drawable.iv_car_hospital, R.drawable.iv_repair_car));
        personList.add(new Person("Nam", false, false, R.drawable.iv_model_and_car, R.drawable.iv_nice_car));
        personList.add(new Person("Quân", false, false, R.drawable.iv_car_hospital, R.drawable.iv_model_and_car));
        personList.add(new Person("Huy", true, true, R.drawable.iv_nice_car, R.drawable.iv_model_and_car));
        personList.add(new Person("Long", false, false, R.drawable.iv_repair_car, R.drawable.iv_car_hospital));
        personList.add(new Person("Ma", false, true, R.drawable.iv_car_hospital, R.drawable.iv_model_and_car));
        personList.add(new Person("Ghost", true, false, R.drawable.iv_car_hospital, R.drawable.iv_repair_car));

        firstP5Adapter = new FirstP5Adapter(this, personList);
        rvFirst.setAdapter(firstP5Adapter);
        rvFirst.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        messageList = new ArrayList<>();
        messageList.add(new Message(false, R.drawable.iv_car_hospital, "Nam", "You: Ăn cơm chưa", true, true, false, "now"));
        messageList.add(new Message(false, R.drawable.iv_model_and_car, "Just You", "You sent a file", true, true, false, "10:23"));
        messageList.add(new Message(true, R.drawable.iv_nice_car, "Hoàng'ss", "Yebbbbbbbbbbbb", false, true, false, "12:23"));
        messageList.add(new Message(false, R.drawable.iv_repair_car, "Oh my chuối", "Nay đi đâu đấy", false, true, false, "21:23"));
        messageList.add(new Message(false, R.drawable.iv_nice_car, "Là tên", "Nay kiểm tra nhé !!!", true, false, false, "Sun"));
        messageList.add(new Message(true, R.drawable.iv_car_hospital, "No name", "Ăn cơm chưa", true, true, false, "23 tháng 6"));
        messageList.add(new Message(false, R.drawable.iv_repair_car, "Nam", "Ăn cơm chưa", true, true, false, "12/10/2017"));
        secondP5Adapter = new SecondP5Adapter(this, messageList);
        rvSeconds.setAdapter(secondP5Adapter);
        rvSeconds.setLayoutManager(new LinearLayoutManager(this));
    }
}
