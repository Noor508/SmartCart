package com.example.devilapplication.API.GET;


import android.graphics.ColorSpace;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Methods {
    @GET("api/unknown")
    Call<Model> getAllData();

}