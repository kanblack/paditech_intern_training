package com.toring.pictureapplication.network.modle;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainObject {

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