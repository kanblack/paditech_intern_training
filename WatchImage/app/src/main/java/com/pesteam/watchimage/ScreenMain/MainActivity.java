package com.pesteam.watchimage.ScreenMain;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.pesteam.watchimage.R;
import com.pesteam.watchimage.Screen5.FragmentScreen5;
import com.pesteam.watchimage.getData.APIService;
import com.pesteam.watchimage.getData.APIUtils;
import com.pesteam.watchimage.getData.ChildScreen1Class;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private android.support.v4.app.FragmentManager fm;
    private int whatFragment;
    public static final int FRAG_1 = 0;
    public static final int FRAG_2 = 1;
    public static final int FRAG_3 = 2;
    private FragmentTransaction ft_tran;
    private List<String> url_image = new ArrayList<>();
    private APIService apiService = APIUtils.getAPIService();

    @BindView(R.id.bt_change_fragment_screen1)
    ImageButton bt_change_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        start();
        getData();
    }

    private void getData() {
        apiService.loadData().enqueue(new Callback<ChildScreen1Class>() {
            @Override
            public void onResponse(Call<ChildScreen1Class> call, Response<ChildScreen1Class> response) {
                if(response.isSuccessful()){
                    url_image = response.body().getData();
                    ft_tran.replace(R.id.frag_screen123, new FragmentScreen1());
                    ft_tran.addToBackStack(null);
                    ft_tran.commitAllowingStateLoss();
                }
            }

            @Override
            public void onFailure(Call<ChildScreen1Class> call, Throwable t) {
                Log.d( "onFailure: ", " false get data");
            }
        });
    }

    @SuppressLint("CommitTransaction")
    public void start() {
        fm = getSupportFragmentManager();
        ft_tran = fm.beginTransaction();
        bt_change_frag.setImageResource(R.drawable.icon_tool_bar_screen1);
    }


    public List<String> getUrls(){
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < url_image.size(); i++) {
            urls.add(url_image.get(i));
        }
        return urls;
    }

    public List<String> getUrl_image() {
        return url_image;
    }

    @Override
    public void onBackPressed() {
        switch (whatFragment){
            case FRAG_1:
                finish();
            case FRAG_2:
                ft_tran.replace(R.id.frag_screen123, new FragmentScreen1());
                ft_tran.addToBackStack(null);
                ft_tran.commitAllowingStateLoss();
                whatFragment = FRAG_1;
                break;
            case FRAG_3:
                ft_tran.replace(R.id.frag_screen123, new FragmentScreen2());
                ft_tran.addToBackStack(null);
                ft_tran.commitAllowingStateLoss();
                whatFragment = FRAG_2;
                break;
        }
    }
}
