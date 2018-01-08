package com.pesteam.mvptest.View;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pesteam.mvptest.R;
import com.pesteam.mvptest.presenters.PresenterLogin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainInterface,View.OnClickListener{

    @BindView(R.id.name)
    TextView tx_name;
    @BindView(R.id.pass)
    TextView tx_pass;
    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.bt_cancel)
    Button bt_cancel;
    PresenterLogin presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        presenter = new PresenterLogin(this);
        bt_login.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
    }

    @SuppressLint("ShowToast")
    @Override
    public void loginsuccess() {
        Toast.makeText(MainActivity.this," Login success!!!",Toast.LENGTH_LONG);
    }

    @SuppressLint("ShowToast")
    @Override
    public void loginfaild() {
        Toast.makeText(MainActivity.this," Login failed!!!",Toast.LENGTH_LONG);
    }

    @Override
    public void onClick(View view) {
        String name = tx_name.getText().toString();
        String pass = tx_pass.getText().toString();
        switch (view.getId()){
            case R.id.bt_login:
                presenter.receiveHandleLogin(name,pass);
                break;
            case R.id.bt_cancel:
                finish();
                break;
        }
    }
}
