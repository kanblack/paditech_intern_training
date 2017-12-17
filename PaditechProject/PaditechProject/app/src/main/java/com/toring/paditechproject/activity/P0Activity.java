package com.toring.paditechproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.P0MyMenuAdapter;
import com.toring.paditechproject.model.P0ItemMenu;

import java.util.ArrayList;
import java.util.List;

public class P0Activity extends AppCompatActivity {
    private RecyclerView rvMenu;
    private List<P0ItemMenu> menuList;
    private P0MyMenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p0);

        rvMenu = this.findViewById(R.id.rv_p0_menu);

        initData();

        setData();
    }

    private void initData() {
        menuList = new ArrayList<>();
        menuList.add(new P0ItemMenu("Màn hình P1", "Màn hình P1", new Intent(this, P1Activity.class)));
        menuList.add(new P0ItemMenu("Màn hình P2", "Màn hình P2", new Intent(this, P2Activity.class)));
        menuList.add(new P0ItemMenu("Màn hình P3", "Màn hình P3", new Intent(this, P3Activity.class)));
        menuList.add(new P0ItemMenu("Màn hình P4", "Màn hình P4", new Intent(this, P4Activity.class)));
        menuList.add(new P0ItemMenu("Màn hình P5", "Màn hình P5", new Intent(this, P5Activity.class)));
    }

    private void setData() {
        menuAdapter = new P0MyMenuAdapter(this, menuList);
        rvMenu.setAdapter(menuAdapter);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
    }
}
