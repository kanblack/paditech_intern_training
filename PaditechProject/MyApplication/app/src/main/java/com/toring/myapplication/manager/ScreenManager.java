package com.toring.myapplication.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by tr on 12/18/17.
 */

public class ScreenManager {
    public static void replaceFragment(AppCompatActivity activityCompat, int id_view, Fragment fragment, boolean addTobackStack) {
        FragmentManager manager = activityCompat.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id_view, fragment);
        if (addTobackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void backFragment(AppCompatActivity appCompatActivity) {
        FragmentManager manager = appCompatActivity.getSupportFragmentManager();
        manager.popBackStack();
    }
}
