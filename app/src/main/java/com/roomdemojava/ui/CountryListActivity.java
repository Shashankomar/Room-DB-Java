package com.roomdemojava.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.roomdemojava.R;
import com.roomdemojava.apiservice.ApiClient;
import com.roomdemojava.apiservice.ApiInterface;
import com.roomdemojava.databinding.ActivityCountryListBinding;
import com.roomdemojava.model.CountryListModel;
import com.roomdemojava.ui.adapter.CountryListAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListActivity extends AppCompatActivity {
    private ActivityCountryListBinding mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = DataBindingUtil.setContentView(this, R.layout.activity_country_list);

        fetchCountryDataFromAPI();

    }

    private void fetchCountryDataFromAPI() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CountryListModel>> call = apiInterface.getFeedData();

        call.enqueue(new Callback<List<CountryListModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CountryListModel>> call, @NotNull Response<List<CountryListModel>> response) {
                Log.e("------", "success " + response.body());

                setRecyclerView(response.body());

            }

            @Override
            public void onFailure(@NotNull Call<List<CountryListModel>> call, @NotNull Throwable t) {
                Log.e("------", "failure " + t.getMessage());
            }
        });
    }

    private void setRecyclerView(List<CountryListModel> countryList) {
        CountryListAdapter adapter = new CountryListAdapter(this, countryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBind.rvCountries.setLayoutManager(layoutManager);
        mBind.rvCountries.setAdapter(adapter);
    }
}