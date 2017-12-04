package com.inigoflores.instafittest.coaches.presenter;

import com.inigoflores.instafittest.coaches.interactor.CoachesInteractor;
import com.inigoflores.instafittest.coaches.interactor.ICoachesInteractor;
import com.inigoflores.instafittest.coaches.view.ICoachInformationView;
import com.inigoflores.instafittest.coaches.view.ICoachesView;

/**
 * Created by inigo on 02/12/17.
 */

public class CoachesPresenter implements ICoachesPresenter {

    private ICoachesInteractor coachesInteractor;

    private ICoachesView coachesView;
    private ICoachInformationView coachInfoView;

    public CoachesPresenter() {
        this.coachesInteractor = new CoachesInteractor(this);
    }

    public void setCoachesView(ICoachesView coachesView){
        this.coachesView = coachesView;
    }

    public void setCoachInformationView(ICoachInformationView coachInfoView){
        this.coachInfoView = coachInfoView;
    }

    @Override
    public void requestCoaches() {
        if(coachesInteractor != null)
            coachesInteractor.requestCoaches();
    }

    @Override
    public void responseCoaches(Object object) {
        coachesView.responseCoaches(object);
    }

    @Override
    public void errorResponse(String error) {
        coachesView.errorResponde(error);
    }

    @Override
    public void requestCoachInformation(Integer id) {
        coachesInteractor.requestCoachInformation(id);
    }

    @Override
    public void responseCoachInformation(Object object) {
        if(coachInfoView != null){
            coachInfoView.responseCoachInformation(object);
        }
    }
}
