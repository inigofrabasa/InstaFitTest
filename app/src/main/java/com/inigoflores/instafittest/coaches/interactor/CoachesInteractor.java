package com.inigoflores.instafittest.coaches.interactor;

import com.inigoflores.instafittest.coaches.presenter.ICoachesPresenter;
import com.inigoflores.instafittest.coaches.repository.Coach;
import com.inigoflores.instafittest.coaches.repository.Coaches;
import com.inigoflores.instafittest.coaches.repository.ICoachesDataService;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by inigo on 02/12/17.
 */

public class CoachesInteractor implements ICoachesInteractor{

    private ICoachesPresenter coachesPresenter;
    private Realm realm;

    public CoachesInteractor(ICoachesPresenter coachesPresenter) {
        this.coachesPresenter = coachesPresenter;
    }

    @Override
    public void requestCoaches() {

        realm = Realm.getDefaultInstance();
        RealmResults<Coach> coaches = getAllCoachesRealm();

        if(coaches == null || coaches.size() == 0){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://dev.instafit.com/api/v3.1/user/697257/a6kt_yP5y8opx9sz_9Pe/catalog_of/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ICoachesDataService service =  retrofit.create(ICoachesDataService.class);

            Call<Coaches> messagesCall = service.getCoaches();
            if(messagesCall != null){
                messagesCall.enqueue(new Callback<Coaches>() {
                    @Override
                    public void onResponse(Call<Coaches> call, Response<Coaches> response) {
                        if(response.body() != null){

                            saveCoaches(response.body());
                            coachesPresenter.responseCoaches(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Coaches> call, Throwable t) {
                        coachesPresenter.errorResponse(t.getMessage());
                    }
                });
            }
        } else {
            Coaches coachesObject = new Coaches();
            coachesObject.setCoaches(coaches);
            coachesPresenter.responseCoaches(coachesObject);
        }
    }

    @Override
    public void requestCoachInformation(Integer id) {
        Coach coach = getCoachInformation(id);
        if(coach != null){
            coachesPresenter.responseCoachInformation(coach);
        }
    }

    private RealmResults<Coach> getAllCoachesRealm(){
        return realm.where(Coach.class).findAll();
    }

    private Coach getCoachInformation(Integer id){
        return realm.where(Coach.class).equalTo("id", id).findFirst();
    }

    private void saveCoaches(Object object){
        final Coaches coaches = (Coaches)object;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for(Coach coach : coaches.getCoaches()){
                    realm.copyToRealmOrUpdate(coach);
                }
            }
        });
    }

    private void deleteAllCoachesRealm(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
}
