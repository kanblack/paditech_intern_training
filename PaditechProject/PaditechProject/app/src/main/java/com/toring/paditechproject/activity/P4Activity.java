package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toring.paditechproject.R;
import com.toring.paditechproject.adapter.NotifacationAdapter;
import com.toring.paditechproject.model.Noti;

import java.util.ArrayList;
import java.util.List;

public class P4Activity extends AppCompatActivity {

    private RecyclerView rvNew, rvEarlier;
    private NotifacationAdapter newAdapter, earlierAdapter;
    private List<Noti> newNoti, earlierNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4);

        rvNew = (RecyclerView) this.findViewById(R.id.rv_new);
        rvEarlier = (RecyclerView) this.findViewById(R.id.rv_earlier);

        getDatanew();
        getDataEarlier();
    }

    public void getDatanew() {
        newNoti = new ArrayList<>();

        List<String> names = new ArrayList<>();
        names.add("Trịnh Huỳnnh");
        names.add("Lan's Nguyễn");
        names.add("Nguyễn Hoàng");
        names.add("Huỳnh ");
        newNoti.add(new Noti(names, 2, "30 minutes ago", false));


        names = new ArrayList<>();
        names.add("Huỳnh ");
        names.add("Trịnh Huỳnnh");
        names.add("Nguyễn Hoàng");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        newNoti.add(new Noti(names, 4, "2 days ago", false));

        newAdapter = new NotifacationAdapter(this, newNoti);
        rvNew.setAdapter(newAdapter);
        rvNew.setLayoutManager(new LinearLayoutManager(this));
    }


    private void getDataEarlier() {
        earlierNoti= new ArrayList<>();

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
        earlierNoti.add(new Noti(names, 2, "now", true));

        names = new ArrayList<>();
        names.add("Huỳnh ");
        earlierNoti.add(new Noti(names, 2, "20 minutes ago", false));

        names = new ArrayList<>();
        names.add("Nguyễn Hoàng");
        names.add("Huỳnh ");
        names.add("Lan's Nguyễn");
        names.add("Trịnh Huỳnnh");
        earlierNoti.add(new Noti(names, 2, "1 hours ago", true));

        names.add("Nguyễn Hoàng");
        names.add("Huỳnh ");
        earlierNoti.add(new Noti(names, 1, "2 hours ago", true));


        names = new ArrayList<>();
        names.add("Huỳnh ");
        names.add("Lan's Nguyễn");
        names.add("Nguyễn Hoàng");
        names.add("Trịnh Huỳnnh");
        names.add("Lan's Nguyễn");
        names.add("Lan's Nguyễn");
        earlierNoti.add(new Noti(names, 3, "4 days ago", true));

        earlierAdapter = new NotifacationAdapter(this, earlierNoti);
        rvEarlier.setAdapter(earlierAdapter);
        rvEarlier.setLayoutManager(new LinearLayoutManager(this));
    }
}
