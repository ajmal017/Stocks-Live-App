package xyz.sahilsood.yumstocks.repositories;

import androidx.lifecycle.LiveData;

import xyz.sahilsood.yumstocks.models.StockDetail;
import xyz.sahilsood.yumstocks.requests.YumRestApiClient;
import xyz.sahilsood.yumstocks.requests.YumWebSocketListener;

public class StocksRepository {
    private static StocksRepository instance;
    private static YumWebSocketListener yumWebSocketListener;
    private static YumRestApiClient yumRestApiClient;

    public static StocksRepository getInstance(){
        if(instance == null){
            instance = new StocksRepository();
        }
        return instance;
    }

    private StocksRepository(){
        yumWebSocketListener = YumWebSocketListener.getInstance();
        yumRestApiClient = YumRestApiClient.getInstance();
    }

    public LiveData<StockDetail> getStocks(){
        return yumRestApiClient.getStocks();
    }

    public void getStocksList(){
        yumWebSocketListener.start();
    }

    public void getStocksDetails(String id){
        yumRestApiClient.getStocksDetails(id);
    }
}
