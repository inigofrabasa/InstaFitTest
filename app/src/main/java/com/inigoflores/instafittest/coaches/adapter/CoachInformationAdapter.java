package com.inigoflores.instafittest.coaches.adapter;

import android.app.Activity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.inigoflores.instafittest.R;
import com.inigoflores.instafittest.application.InstaFitApplication;
import com.inigoflores.instafittest.coaches.repository.Coach;

/**
 * Created by inigo on 03/12/17.
 */

public class CoachInformationAdapter {

    private TextView coachName;
    private ImageView coachImage;
    private WebView coachDescription;

    private Activity activity;
    private Coach coach;

    public CoachInformationAdapter(Activity activity, Object object) {
        this.activity = activity;
        this.coach = (Coach)object;

        coachName = activity.findViewById(R.id.tv_coach_name);
        coachImage = activity.findViewById(R.id.ci_profile_image);
        coachDescription = activity.findViewById(R.id.wb_coach_description);
        bindData();
    }

    private void bindData(){
        coachName.setText(coach.getName());
        coachImage.setImageBitmap(InstaFitApplication.bitmapInternal.loadImageFromStorage(coach.getAvatarLocal(), coach.getName()));
        coachDescription.loadData(coach.getDescription(), "text/html; charset=utf-8", "UTF-8");
    }
}
