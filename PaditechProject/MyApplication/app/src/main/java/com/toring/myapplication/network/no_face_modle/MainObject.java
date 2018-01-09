package com.toring.myapplication.network.no_face_modle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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