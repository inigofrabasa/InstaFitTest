package com.inigoflores.instafittest.coaches.presenter;

import com.inigoflores.instafittest.coaches.view.ICoachInformationView;
import com.inigoflores.instafittest.coaches.view.ICoachesView;

/**
 * Created by inigo on 02/12/17.
 */

public interface ICoachesPresenter {
    void setCoachesView(ICoachesView coachesView);
    void setCoachInformationView(ICoachInformationView coachInfoView);
    void requestCoaches();
    void responseCoaches(Object object);
    void errorResponse(String error);
    void requestCoachInformation(Integer id);
    void responseCoachInformation(Object object);

}
