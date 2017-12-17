package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.P4NotificationAdapter;
import com.toring.paditechproject.model.P4Notification;

import java.util.ArrayList;
import java.util.List;

public class P4Activity extends AppCompatActivity {

    private RecyclerView rvNew, rvEarlier;
    private P4NotificationAdapter newAdapter, earlierAdapter;
    private List<P4Notification> newNotificationListList, earlierP4NotificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4);

        rvNew =  this.findViewById(R.id.rv_new);
        rvEarlier = this.findViewById(R.id.rv_earlier);

        getDataNew();
        getDataEarlier();
    }

    public void getDataNew() {
        newNotificationListList = new ArrayList<>();

        List<String> names = new ArrayList<>();
        names.add("Trịnh Huỳnnh");
        names.add("Lan's Nguyễn");
        names.add("Nguyễn Hoàng");
        names.add("Huỳnh ");
        newNotificationListList.add(new P4Notification(names, 2, "30 minutes ago", false));


        names = new ArrayList<>();
        names.add("Huỳnh ");
        names.add("Trịnh Huỳnnh");
        names.add("Nguyễn Hoàng");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        newNotificationListList.add(new P4Notification(names, 4, "2 days ago", false));

        newAdapter = new P4NotificationAdapter(this, newNotificationListList);
        rvNew.setAdapter(newAdapter);
        rvNew.setLayoutManager(new LinearLayoutManager(this));
    }


    private void getDataEarlier() {
        earlierP4NotificationList = new ArrayList<>();

        List<String> names = new ArrayList<>();


        names = new ArrayList<>();
        names.add("Trịnh Huỳnnh");
        names.add("Nguyễn Hoàng");
        names.add("Huỳnh ");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        earlierP4NotificationList.add(new P4Notification(names, 2, "now", true));

        names = new ArrayList<>();
        names.add("Huỳnh ");
        earlierP4NotificationList.add(new P4Notification(names, 2, "20 minutes ago", false));

        names = new ArrayList<>();
        names.add("Nguyễn Hoàng");
        names.add("Huỳnh ");
        names.add("Lan's Nguyễn");
        names.add("Trịnh Huỳnnh");
        earlierP4NotificationList.add(new P4Notification(names, 2, "1 hours ago", true));

        names.add("Nguyễn Hoàng");
        names.add("Huỳnh ");
        earlierP4NotificationList.add(new P4Notification(names, 1, "2 hours ago", true));


        names = new ArrayList<>();
        names.add("Huỳnh ");
        names.add("Lan's Nguyễn");
        names.add("Nguyễn Hoàng");
        names.add("Trịnh Huỳnnh");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        earlierP4NotificationList.add(new P4Notification(names, 3, "4 days ago", true));

        earlierAdapter = new P4NotificationAdapter(this, earlierP4NotificationList);
        rvEarlier.setAdapter(earlierAdapter);
        rvEarlier.setLayoutManager(new LinearLayoutManager(this));
    }
}
