package com.svmexample.rxjava.Utils;


import android.content.Context;

import com.svmexample.rxjava.Api.Service;

import javax.inject.Inject;

public class PrefUtils {

    @Inject
    Service service;

    private static PrefUtils utils;


    public static PrefUtils getUtils(Context context){
        if(utils==null){
            utils = new PrefUtils(context);
        }
        return utils;
    }
    private static final String TAG = "PrefUtils";


    Context context;



    private PrefUtils(Context context) {
        this.context=context;
    }

    public void getResults(int pos){





    }



    public void geInitialtResults(){





    }




}
