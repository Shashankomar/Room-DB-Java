package com.roomdemojava.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.roomdemojava.R;
import com.roomdemojava.apiservice.ApiClient;
import com.roomdemojava.apiservice.ApiInterface;
import com.roomdemojava.database.DBClient;
import com.roomdemojava.database.entity.CountryListEntity;
import com.roomdemojava.databinding.ActivityCountryListBinding;
import com.roomdemojava.model.CountryListModel;
import com.roomdemojava.ui.adapter.CountryListAdapter;
import com.roomdemojava.utils.InternetConnection;

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
        getCountryData();
    }

    private void getCountryData() {
        if (InternetConnection.checkConnection(this)) {
            fetchCountryDataFromAPI();
        } else {
            getDataFromDB();
        }
    }

    private void fetchCountryDataFromAPI() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CountryListModel>> call = apiInterface.getFeedData();
        call.enqueue(new Callback<List<CountryListModel>>() {

            @Override
            public void onResponse(@NotNull Call<List<CountryListModel>> call,
                                   @NotNull Response<List<CountryListModel>> response) {
                List<CountryListModel> countryInfoList = response.body();
                if (countryInfoList != null) {
                    deleteDataFromDB();
                    insertDataIntoDB(countryInfoList);
                    getDataFromDB();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<CountryListModel>> call, @NotNull Throwable t) {
                Log.e("------", "failure " + t.getMessage());
            }
        });
    }

    private void deleteDataFromDB() {
        class DeleteData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DBClient.newInstance(getApplicationContext())
                        .appDatabase()
                        .CountryListDao()
                        .delete();
                return null;
            }
        }
        DeleteData del = new DeleteData();
        del.execute();
    }

    private void insertDataIntoDB(List<CountryListModel> countryInfoList) {
        class InsertData extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                CountryListEntity countryListEntity = new CountryListEntity();
                int index = 0;
                for (int i = 0; i < countryInfoList.size(); i++) {
                    CountryListModel data = countryInfoList.get(i);
                    countryListEntity.setCountryName(data.name);
                    countryListEntity.setCapital(data.capital);
                    countryListEntity.setRegion(data.region);
                    countryListEntity.setSubRegion(data.subregion);
                    countryListEntity.setFlag(data.flag);
                    countryListEntity.setPopulation(data.population);
                    countryListEntity.setLanguage(data.languages.get(index).name);
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < data.borders.size(); k++) {
                        sb.append(data.borders.get(k)).append(", ");
                    }
                    if (sb.length() > 2){
                        sb = sb.deleteCharAt(sb.length() - 2);
                    }
                    countryListEntity.setBorders(sb.toString());

                    DBClient.newInstance(getApplicationContext())
                            .appDatabase()
                            .CountryListDao()
                            .insert(countryListEntity);
                }
                return null;
            }
        }
        InsertData insertData = new InsertData();
        insertData.execute();
    }

    private void getDataFromDB() {
        class GetData extends AsyncTask<Void, Void, List<CountryListEntity>> {

            @Override
            protected List<CountryListEntity> doInBackground(Void... voids) {
                return DBClient.newInstance(getApplicationContext())
                        .appDatabase()
                        .CountryListDao()
                        .getAllDataFromRoom();
            }

            @Override
            protected void onPostExecute(List<CountryListEntity> countryListEntities) {
                super.onPostExecute(countryListEntities);
                setRecyclerView(countryListEntities);
            }
        }
        GetData getData = new GetData();
        getData.execute();
    }

    private void setRecyclerView(List<CountryListEntity> countryList) {
        CountryListAdapter adapter = new CountryListAdapter(this, countryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBind.rvCountries.setLayoutManager(layoutManager);
        mBind.rvCountries.setAdapter(adapter);
    }
}