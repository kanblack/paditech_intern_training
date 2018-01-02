package com.pesteam.watchimage.ScreenMain;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.pesteam.watchimage.R;
import com.pesteam.watchimage.facebook.Albums;
import com.pesteam.watchimage.facebook.GetFbData;
import com.pesteam.watchimage.getData.APIService;
import com.pesteam.watchimage.getData.APIUtils;
import com.pesteam.watchimage.getData.ChildScreen1Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    public android.support.v4.app.FragmentManager fm;
    private int whatFragment;
    private int whatStyle;
    private List<String> permisstionFb = Arrays.asList("email", "public_profile", "user_photos");
    CallbackManager callbackManager;
    public static final int FRAG_1 = 0;
    public static final int FRAG_2 = 1;
    public static final int FRAG_3 = 2;
    public static final int STYPE_NOTFB = 0;
    public static final int STYLE_ALBUMS = 1;
    public static final int STYLE_PHOTOS = 2;
    public FragmentTransaction ft_tran;
    private List<String> url_image = new ArrayList<>();
    private APIService apiService = APIUtils.getAPIService();
    private Bitmap frame;
    private List<Albums> albums = new ArrayList<>();
    private AccessToken accessToken;
    private GetFbData getFbData = new GetFbData(this);

    @BindView(R.id.bt_change_fragment_screen1)
    ImageButton bt_change_frag;
    @BindView(R.id.bt_login_fb_screen1)
    ImageButton bt_login_logout;
    @BindView(R.id.progress)
    public ProgressBar progressBar;
    @BindView(R.id.tex_title_toolbar_screen1)
    public TextView tex_title_toolbarl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        start();
    }

    public void setWhatFragment(int whatFragment) {
        this.whatFragment = whatFragment;
    }

    public int getWhatFragment() {
        return whatFragment;
    }

    private void getDataFromService() {
        apiService.loadData().enqueue(new Callback<ChildScreen1Class>() {
            @Override
            public void onResponse(Call<ChildScreen1Class> call, Response<ChildScreen1Class> response) {
                if (response.isSuccessful()) {
                    albums.clear();
                    url_image = response.body().getData();
                    for (int i = 0; i < url_image.size(); i++) {
                        albums.add(new Albums("", "", url_image.get(i), 0));
                    }
                    changeDataInFragment();
                }
            }

            @Override
            public void onFailure(Call<ChildScreen1Class> call, Throwable t) {
                Log.d("onFailure: ", " false get data");
            }
        });
    }

    @SuppressLint("CommitTransaction")
    public void start() {
        callbackManager = CallbackManager.Factory.create();
        fm = getSupportFragmentManager();
        bt_change_frag.setImageResource(R.drawable.icon_tool_bar_screen1);
        if (AccessToken.getCurrentAccessToken() == null) {
            setWhatStyle(STYPE_NOTFB);
            getDataFromService();
            changeLoginLogout(true);
        } else {
            changeLoginLogout(false);
            getFbData.getDataAlbumsFromFb(AccessToken.getCurrentAccessToken(), "");
        }
    }
    public void changeLoginLogout(final boolean needLogin) {
        if (needLogin) {
            bt_login_logout.setImageResource(R.drawable.icon_login_screen1);
            bt_login_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setWhatStyle(STYLE_ALBUMS);
                    registerCallback();
                    LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, permisstionFb);
                    changeLoginLogout(false);
                }
            });
        } else {
            bt_login_logout.setImageResource(R.drawable.icon_logout_screen1);
            bt_login_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setWhatStyle(STYPE_NOTFB);
                    LoginManager.getInstance().logOut();
                    changeLoginLogout(true);
                    getDataFromService();
                    tex_title_toolbarl.setText("HOME");
                }
            });
        }
    }

    public void changeDataInFragment() {
        if (this.getWhatFragment() == MainActivity.FRAG_1) {
            this.ft_tran = this.fm.beginTransaction();
            this.ft_tran.replace(R.id.frag_screen123, new FragmentScreen1());
            this.ft_tran.addToBackStack(null);
            this.ft_tran.commitAllowingStateLoss();
        }
        if (this.getWhatFragment() == MainActivity.FRAG_2) {
            this.ft_tran = this.fm.beginTransaction();
            this.ft_tran.replace(R.id.frag_screen123, new FragmentScreen2());
            this.ft_tran.addToBackStack(null);
            this.ft_tran.commitAllowingStateLoss();
        }
        if (this.getWhatFragment() == MainActivity.FRAG_3) {
            this.ft_tran = this.fm.beginTransaction();
            this.ft_tran.replace(R.id.frag_screen123, new FragmentScreen3());
            this.ft_tran.addToBackStack(null);
            this.ft_tran.commitAllowingStateLoss();
        }
    }
    private void registerCallback() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        getFbData.getDataAlbumsFromFb(loginResult.getAccessToken(), "");
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }


    public List<Albums> getAlbums() {
        return albums;
    }

    public List<String> getUrl_image() {
        return url_image;
    }

    public int getWhatStyle() {
        return whatStyle;
    }

    public void setWhatStyle(int whatStyle) {
        this.whatStyle = whatStyle;
    }

    @Override
    public void onBackPressed() {
        switch (whatFragment) {
            case FRAG_1:
                switch (whatStyle){
                    case STYLE_PHOTOS:
                        getFbData.getDataAlbumsFromFb(AccessToken.getCurrentAccessToken(), "");
                        setWhatStyle(STYLE_ALBUMS);
                        break;
                    case STYPE_NOTFB:
                    case STYLE_ALBUMS:
                        finish();
                }
                break;
            case FRAG_2:
                switch (whatStyle){
                    case STYLE_PHOTOS:
                        getFbData.getDataAlbumsFromFb(AccessToken.getCurrentAccessToken(), "");
                        setWhatStyle(STYLE_ALBUMS);
                        break;
                    case STYPE_NOTFB:
                    case STYLE_ALBUMS:
                        fm.beginTransaction().replace(R.id.frag_screen123,new FragmentScreen1()).addToBackStack(null).commit();
                }
                break;
            case FRAG_3:
                switch (whatStyle){
                    case STYLE_PHOTOS:
                    case STYPE_NOTFB:
                    case STYLE_ALBUMS:
                        fm.beginTransaction().replace(R.id.frag_screen123,new FragmentScreen2()).addToBackStack(null).commit();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}
