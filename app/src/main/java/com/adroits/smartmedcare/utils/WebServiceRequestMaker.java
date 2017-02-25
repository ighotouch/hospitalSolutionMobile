package com.adroits.smartmedcare.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;


import com.adroits.smartmedcare.application.ElearnApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by imatthew on 10/13/2016.
 */

public class WebServiceRequestMaker {
    String TAG = "WebServiceRequestMaker";
    @Inject
    Retrofit retrofit;

    @Inject
    Context appContext;
    Realm realm;

    @Inject
    public WebServiceRequestMaker() {
        ElearnApplication.getComponent().inject(this);
        realm = Realm.getDefaultInstance();
    }

//    public void getPropertyUsedPinsByLgaName(String lga,final ResponseInterface responseInterface){
//        if (AppUtils.isNetworkAvailable(appContext)) {
//
//            PropertyService propertyService =
//                    retrofit.create(PropertyService.class);
//
//
//            final PropertyUsedPinDao propertyUsedPinDao;
//
//            // get the note DAO
//            DaoSession daoSession = ((ElearnApplication) appContext).getDaoSession();
//            propertyUsedPinDao = daoSession.getPropertyUsedPinDao();
//            List<PropertyUsedPin> result = propertyUsedPinDao.queryBuilder()
//                    .where(PropertyUsedPinDao.Properties.Lga.eq(lga))
//                    .list();
//
//            Log.d(TAG, "Property pin total size: " + result.size());
//            String myDate = "none";
//            if (result.size() > 0) {
//                Log.d(TAG, "Last use date property pin: " + result.get(0).toString());
//
//                long timestamp = Long.parseLong(result.get(0).getCreated());
//                Date d = new Date(timestamp);
//                myDate = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(d);
//                Log.d(TAG, ":::::::::::::::::::::::::::::::::::::::::::::::::::: LAST PIN USED DATE: " + myDate);
//
//            }
//
//
//            Call<List<PropertyUsedPin>> call = propertyService.getPropertyUsedPins(myDate,lga);
//
//            call.enqueue(new Callback<List<PropertyUsedPin>>() {
//
//                @Override
//                public void onResponse(Call<List<PropertyUsedPin>> call, Response<List<PropertyUsedPin>> response) {
//                    try {
//                        propertyUsedPinDao.insertOrReplaceInTx(response.body());
//                        responseInterface.onPropertyUsedPinUpdated();
//                    }catch (Exception e){
//                        responseInterface.callFailed(e.getMessage());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<PropertyUsedPin>> call, Throwable t) {
//                    responseInterface.callFailed(t.getMessage());
//                }
//            });
//        } else {
//            if (responseInterface != null) {
//                responseInterface.noIntentConnection();
//
//            }
//        }
//    }

}
