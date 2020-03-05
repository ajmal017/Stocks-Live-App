package xyz.sahilsood.yumstocks.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.sahilsood.yumstocks.models.StockDetail;

public class YumRestApiClient {
    private static final String TAG = "YumStocksApiClient";
    private static YumRestApiClient instance;
    private MutableLiveData<StockDetail> mStocks;

    private YumRestApiClient(){
        mStocks = new MutableLiveData<>();
    }

    public static YumRestApiClient getInstance(){
        if(instance == null){
            instance = new YumRestApiClient();
        }
        return instance;
    }

    public LiveData<StockDetail> getStocks(){
        return mStocks;
    }

    public void getStocksDetails(String id){

        Call<StockDetail> call = ServiceGenerator.getYumStocksApi().getStockDetail(id);
        call.enqueue(new Callback<StockDetail>() {
            @Override
            public void onResponse(Call<StockDetail> call, Response<StockDetail> response) {
                if(response.isSuccessful() || response.code()==200){
                    StockDetail stocks = response.body();
                    mStocks.postValue(stocks);
                }
            }
            @Override
            public void onFailure(Call<StockDetail> call, Throwable t) {
                Log.d(TAG,"onResponse Error: "+t.toString());
                System.out.println(t.getMessage());
            }
        });
    }

}