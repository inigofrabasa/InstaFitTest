package com.inigoflores.instafittest.application;

import android.app.Application;
import android.content.Context;

import com.inigoflores.instafittest.coaches.presenter.CoachesPresenter;
import com.inigoflores.instafittest.coaches.presenter.ICoachesPresenter;
import com.inigoflores.instafittest.coaches.utilities.BitmapInternal;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by inigo on 03/12/17.
 */

public class InstaFitApplication extends Application {

    public static ICoachesPresenter coachesPresenter;
    public static String COACH_ID_INFORMATION = "coachIdInformation";
    public static BitmapInternal bitmapInternal;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        coachesPresenter = new CoachesPresenter();
        bitmapInternal = new BitmapInternal();
        context = getApplicationContext();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .name("coaches.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
