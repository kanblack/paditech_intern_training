package com.pesteam.mvptest.presenters;

import com.pesteam.mvptest.View.MainInterface;
import com.pesteam.mvptest.models.ModelLogin;

/**
 * Created by bangindong on 1/5/2018.
 */

public class PresenterLogin implements PresenterLoginIn {

    private ModelLogin modelLogin;
    private MainInterface callback;

    public PresenterLogin(MainInterface callback) {
        this.callback = callback;
        modelLogin = new ModelLogin(this);

    }

    public void receiveHandleLogin(String name, String pass){
        modelLogin.handleLogin(name,pass);
    }

    @Override
    public void loginsuccess() {
        callback.loginsuccess();
    }

    @Override
    public void loginfaild() {
        callback.loginfaild();
    }
}
