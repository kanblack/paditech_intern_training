package com.toring.myapplication.network.modle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

public class MainObject{

@SerializedName("data")
@Expose
private List<String> data = null;

public List<String> getData() {
return data;
}

public void setData(List<String> data) {
this.data = data;
}

}