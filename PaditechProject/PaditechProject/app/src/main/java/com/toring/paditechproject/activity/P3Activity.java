package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.TaskAdapter;
import com.toring.paditechproject.model.Task;

import java.util.ArrayList;
import java.util.List;

public class P3Activity extends AppCompatActivity {

    private RecyclerView rvTask;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);

        rvTask = (RecyclerView) this.findViewById(R.id.rv_task);

        getData();
    }

    public void getData() {
        taskList = new ArrayList<>();

        taskList.add(new Task("Finish landing page concept", "#00FF00"));
        taskList.add(new Task("Design app illustrations", "#0000FF"));
        taskList.add(new Task("Javascript traning", "#FF0000"));
        taskList.add(new Task("Surprise Party for Matt", "#AAFF00"));
        taskList.add(new Task("Create project", "#00FFCC"));

        taskAdapter = new TaskAdapter(this, taskList);

        rvTask.setAdapter(taskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(this));
    }
}
