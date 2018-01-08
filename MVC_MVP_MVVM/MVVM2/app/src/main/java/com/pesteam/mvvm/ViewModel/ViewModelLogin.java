package com.pesteam.mvvm.ViewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.pesteam.mvvm.MainActivity;
import com.pesteam.mvvm.R;
import com.pesteam.mvvm.models.ModelLogin;

/**
 * Created by bangindong on 1/5/2018.
 */

public class ViewModelLogin extends BaseObservable {

    private ModelLogin modelLogin = new ModelLogin();
    private MainActivity mainActivity;

    private String userName;
    private String passWord;

    public ViewModelLogin(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public boolean receiveHandleLogin(){
        return modelLogin.checkAccount(userName,passWord);
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;

        notifyPropertyChanged(R.id.name);
    }

    @Bindable
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;

        notifyPropertyChanged(R.id.pass);
    }
}
