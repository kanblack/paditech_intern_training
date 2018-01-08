package com.pesteam.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pesteam.mvvm.ClickEvent.OnclickListener;
import com.pesteam.mvvm.ViewModel.ViewModelLogin;
import com.pesteam.mvvm.databinding.ActivityMainBinding;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final ViewModelLogin gameViewModel = new ViewModelLogin(this);
        activityGameBinding.setModellogin(gameViewModel);

        activityGameBinding.setClick(new OnclickListener() {
            @Override
            public void Onclick() {
                if(activityGameBinding.getModellogin().receiveHandleLogin()){
                    Toast.makeText(MainActivity.this," Login success!!!",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this," Login failed!!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
