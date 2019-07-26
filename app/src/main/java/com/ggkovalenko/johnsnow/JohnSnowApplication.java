package com.ggkovalenko.johnsnow;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ggkovalenko.johnsnow.data.ExternalDatasource;

public final class JohnSnowApplication extends Application {

    @NonNull
    private ExternalDatasource externalDatasource;

    @Override
    public void onCreate() {
        super.onCreate();
        externalDatasource = new ExternalDatasource();
    }

    @NonNull
    public ExternalDatasource getExternalDatasource() {
        return externalDatasource;
    }

}
