package com.example.productapi_fakestore_foma.repositories;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.productapi_fakestore_foma.models.ModelM;
import com.example.productapi_fakestore_foma.remote_data.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JamRepository {
    final MutableLiveData<List<ModelM>> data = new MutableLiveData<>();
    public LiveData<List<ModelM>> getDashJeminyList() {
        Call<List<ModelM>> apiCall = RetrofitClient.getInstance().getApi().getStoreProducts();
        apiCall.enqueue(new Callback<List<ModelM>>() {
            @Override
            public void onResponse(Call<List<ModelM>> call,
                                   Response<List<ModelM>> response) {
                if (response.body()!=null) {
                    data.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<ModelM>> call, Throwable throwable) {
                Log.e("Tag", "NO DATA " + throwable.getLocalizedMessage());
                data.postValue(null);
            }
        });
    return data;
    }

}
