package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.P3TaskAdapter;
import com.toring.paditechproject.model.P3Task;

import java.util.ArrayList;
import java.util.List;

public class P3Activity extends AppCompatActivity {

    private RecyclerView rvTask;
    private P3TaskAdapter p3TaskAdapter;
    private List<P3Task> p3TaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3);

        Toolbar toolbar = this.findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);

        rvTask = this.findViewById(R.id.rv_task);

        getData();
    }

    public void getData() {
        p3TaskList = new ArrayList<>();

        p3TaskList.add(new P3Task("Finish landing page concept", "#00FF00"));
        p3TaskList.add(new P3Task("Design app illustrations", "#0000FF"));
        p3TaskList.add(new P3Task("Javascript traning", "#FF0000"));
        p3TaskList.add(new P3Task("Surprise Party for Matt", "#AAFF00"));
        p3TaskList.add(new P3Task("Create project", "#00FFCC"));

        p3TaskAdapter = new P3TaskAdapter(this, p3TaskList);

        rvTask.setAdapter(p3TaskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(this));
    }
}
