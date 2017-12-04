package com.inigoflores.instafittest.coaches.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.inigoflores.instafittest.R;
import com.inigoflores.instafittest.application.InstaFitApplication;
import com.inigoflores.instafittest.coaches.adapter.CoachesAdapter;

public class CoachesActivity extends AppCompatActivity implements ICoachesView{

    private RecyclerView coachesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coaches);

        coachesRecycler = findViewById(R.id.rv_coaches);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        coachesRecycler.setLayoutManager(linearLayoutManager);

        InstaFitApplication.coachesPresenter.setCoachesView(this);
        InstaFitApplication.coachesPresenter.requestCoaches();
    }

    @Override
    public void responseCoaches(Object object) {
        if(object != null){
            CoachesAdapter coachesAdapter
                    = new CoachesAdapter(R.layout.coach_item_view, this, object);
            coachesRecycler.setAdapter(coachesAdapter);
            coachesRecycler.invalidate();
        }
    }

    @Override
    public void errorResponde(String error) {
        Log.v("Coaches error response", error);
    }
}
