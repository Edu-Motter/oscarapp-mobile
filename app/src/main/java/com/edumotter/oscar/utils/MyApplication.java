package com.edumotter.oscar.utils;

import android.app.Application;

public class MyApplication extends Application {

    private String mGlobalVarValue = "sim";

    public String getGlobalVarValue() {
        return mGlobalVarValue;
    }

    public void setGlobalVarValue(String str) {
        mGlobalVarValue = str;
    }
}