package com.inigoflores.instafittest.coaches.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.inigoflores.instafittest.R;
import com.inigoflores.instafittest.application.InstaFitApplication;
import com.inigoflores.instafittest.coaches.adapter.CoachInformationAdapter;

public class CoachInformationActivity extends AppCompatActivity implements ICoachInformationView{

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_information);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra(InstaFitApplication.COACH_ID_INFORMATION)){
            Integer id = (Integer)getIntent().getExtras().get(InstaFitApplication.COACH_ID_INFORMATION);
            InstaFitApplication.coachesPresenter.setCoachInformationView(this);
            InstaFitApplication.coachesPresenter.requestCoachInformation(id);
        }
    }

    @Override
    public void responseCoachInformation(Object object) {
        if(object != null){
            CoachInformationAdapter informationAdapter = new CoachInformationAdapter(this, object);
        }
    }
}
