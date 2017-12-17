package com.toring.paditechproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.toring.paditechproject.R;
import com.toring.paditechproject.network.RetrofitFactory;
import com.toring.paditechproject.network.model.P2MainObject;
import com.toring.paditechproject.network.service.ServiceLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class P2Activity extends AppCompatActivity {
    private TextView etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2);

        etUsername = this.findViewById(R.id.et_user_name);
        etPassword = this.findViewById(R.id.et_password);

        Button btLogin = this.findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceLogin serviceLogin = RetrofitFactory.getInstance().createServiceClass(ServiceLogin.class);
                serviceLogin.login(etUsername.getText().toString(), etPassword.getText().toString())
                        .equals(new Callback<P2MainObject>() {
                            @Override
                            public void onResponse(Call<P2MainObject> call, Response<P2MainObject> response) {
                                P2MainObject mainObject = response.body();
                                Toast.makeText(P2Activity.this, "token: "
                                        + mainObject.getData().getUser_id()
                                        + "..." +
                                        mainObject.getData().getAccess_token(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<P2MainObject> call, Throwable t) {

                            }
                        });
            }
        });
    }
}
