package com.toring.paditechproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.MyMenuAdapter;
import com.toring.paditechproject.item.ItemMenu;

import java.util.ArrayList;
import java.util.List;

public class P0Activity extends AppCompatActivity {
    private RecyclerView rvMenu;
    private List<ItemMenu> menuList;
    private MyMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p0);

        rvMenu = (RecyclerView) this.findViewById(R.id.rv_menu);

        initData();

        setData();

    }

    private void initData() {
        menuList = new ArrayList<>();
        menuList.add(new ItemMenu("Màn hình P1", "Màn hình P1", new Intent(this, P1Activity.class)));
        menuList.add(new ItemMenu("Màn hình P2", "Màn hình P2", new Intent(this, P2Activity.class)));
        menuList.add(new ItemMenu("Màn hình P3", "Màn hình P3"));
        menuList.add(new ItemMenu("Màn hình P4", "Màn hình P4"));
        menuList.add(new ItemMenu("Màn hình P5", "Màn hình P5"));
    }

    private void setData() {
        adapter = new MyMenuAdapter(this, menuList);
        rvMenu.setAdapter(adapter);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
    }
}
