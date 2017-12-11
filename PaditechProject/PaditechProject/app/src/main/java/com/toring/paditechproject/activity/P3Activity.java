package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.toring.paditechproject.R;

public class P3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
    }
}
