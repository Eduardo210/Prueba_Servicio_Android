package com.quetzal.prueba_servicio_android.Retrofit;

import com.quetzal.prueba_servicio_android.jsonParceable.ResultJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchInformation {

    @GET("similar")
    Call<ResultJSON> getSearch(
            @Query("q") String q,
            @Query("info") String info,
            @Query("type") String type,
            @Query("k") String k
    );
}
