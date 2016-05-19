/*
 * Copyright (c) 2016.  Lukaround Inc ;This program is free software: you can &#10;
 * redistribute it and/or modify;it under the terms of the Lukaround Inc Public License as &#10;
 * published by Lukaround Software Foundation, either version 3 of the License or (at your option) any later version ;&#10;This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; &#10;without even the implied warranty of;MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.&#10;  See www.lukaround.org/developer/licence
 */

package pinnacle.org.nidd;

import android.app.Application;
import android.content.res.Configuration;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import pinnacle.org.nidd.model.Operator;

/**
 * @architect: Samuel Ekpe(Team Lead)
 * @author: Genius
 * @reviewers: Lukaround Android Team
 * Date: 4/25/16
 * Time: 6:52 AM
 */
public class NiddApplication extends Application {
    private final String TAG=getClass().getSimpleName();
    private Map<String, Fragment.SavedState> savedStateMap;
    private static NiddApplication singleton;
    private Operator operator;



    /**
     * singleton pattern
     * @return
     */
    public NiddApplication getInstance(){
        return singleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        savedStateMap = new HashMap<String, Fragment.SavedState> ();
        singleton = this;
     }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();}

    public void setFragmentSavedState(String key, Fragment.SavedState state){
        savedStateMap.put(key, state);
    }

    public Fragment.SavedState getFragmentSavedState(String key){
        return savedStateMap.get(key);
    }

}
