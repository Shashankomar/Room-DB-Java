package com.roomdemojava.apiservice;

import com.roomdemojava.model.CountryListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("region/asia")
    Call<List<CountryListModel>> getFeedData();
}
