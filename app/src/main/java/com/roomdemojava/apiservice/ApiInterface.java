package com.roomdemojava.apiservice;

import com.roomdemojava.model.CountryListModel;
import com.roomdemojava.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET(Constants.IEndPointConstants.RESION_ASIA)
    Call<List<CountryListModel>> getFeedData();
}