package xyz.sahilsood.yumstocks.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import xyz.sahilsood.yumstocks.models.StockDetail;
import xyz.sahilsood.yumstocks.repositories.StocksRepository;

public class StocksViewModel extends ViewModel {
    private StocksRepository stocksRepository;


    public StocksViewModel() {
        stocksRepository = StocksRepository.getInstance();
    }

    public LiveData<StockDetail> getStocks(){
        return stocksRepository.getStocks();
    }

    public void getStocksList(){
        stocksRepository.getStocksList();
    }

    public void getStocksDetail(String id){
        stocksRepository.getStocksDetails(id);
    }
}
