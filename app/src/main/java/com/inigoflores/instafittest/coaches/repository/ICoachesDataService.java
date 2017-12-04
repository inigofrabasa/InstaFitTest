package com.inigoflores.instafittest.coaches.repository;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by inigo on 02/12/17.
 */

public interface ICoachesDataService {
    @GET("coaches")
    Call<Coaches> getCoaches();
}
