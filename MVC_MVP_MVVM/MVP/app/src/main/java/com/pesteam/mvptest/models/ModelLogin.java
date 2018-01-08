package com.pesteam.mvptest.models;

import com.pesteam.mvptest.models.objects.Account;
import com.pesteam.mvptest.presenters.PresenterLoginIn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangindong on 1/5/2018.
 */

public class ModelLogin {

    private PresenterLoginIn callback;
    private List<Account> accounts;

    public ModelLogin(PresenterLoginIn callback) {
        this.callback = callback;
        accounts = new ArrayList<>();
        accounts.add(new Account("bangindong","123"));
        accounts.add(new Account("conbo","123"));
        accounts.add(new Account("conboconbo","123"));
        accounts.add(new Account("bangin","123"));
    }

    public void handleLogin(String name, String pass){
       if(checkAccount(name,pass)){
           callback.loginsuccess();
       } else {
           callback.loginfaild();
       }
    }

    private boolean checkAccount(String name, String pass){
        for (int i = 0; i < accounts.size(); i++) {
            if (name.equals(accounts.get(i).getName()) && pass.equals(accounts.get(i).getPass())){
                return true;
            }
        }
        return false;
    }
}
