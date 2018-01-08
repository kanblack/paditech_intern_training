package com.pesteam.mvvm.models;

import com.pesteam.mvvm.models.objects.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangindong on 1/5/2018.
 */

public class ModelLogin {

    private List<Account> accounts;

    public ModelLogin() {
        accounts = new ArrayList<>();
        accounts.add(new Account("bangindong","123"));
        accounts.add(new Account("conbo","123"));
        accounts.add(new Account("conboconbo","123"));
        accounts.add(new Account("bangin","123"));
    }

    public boolean checkAccount(String name, String pass) {
        for (int i = 0; i < accounts.size(); i++) {
            if (name.equals(accounts.get(i).getName()) && pass.equals(accounts.get(i).getPass())){
                return true;
            }
        }
        return false;
    }
}
