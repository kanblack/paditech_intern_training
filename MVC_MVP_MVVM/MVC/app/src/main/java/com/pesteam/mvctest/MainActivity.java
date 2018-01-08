package com.pesteam.mvctest;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pesteam.mvctest.models.ModelLogin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.name)
    TextView tx_name;
    @BindView(R.id.pass)
    TextView tx_pass;
    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.bt_cancel)
    Button bt_cancel;
    ModelLogin model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        model = new ModelLogin();
        bt_login.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
    }

    @SuppressLint("ShowToast")
    @Override
    public void onClick(View view) {
        String name = tx_name.getText().toString();
        String pass = tx_pass.getText().toString();
        switch (view.getId()){
            case R.id.bt_login:
                if(model.checkAccount(name,pass)){
                    Toast.makeText(MainActivity.this," Login success!!!",Toast.LENGTH_LONG);
                } else {
                    Toast.makeText(MainActivity.this," Login failed!!!",Toast.LENGTH_LONG);
                }
                break;
            case R.id.bt_cancel:
                finish();
                break;
        }
    }
}
