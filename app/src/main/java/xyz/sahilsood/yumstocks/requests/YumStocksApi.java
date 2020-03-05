package xyz.sahilsood.yumstocks.requests;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.sahilsood.yumstocks.models.StockDetail;
import xyz.sahilsood.yumstocks.models.StockTicker;

public interface YumStocksApi {
    @GET("api/stocks/{id}")
    Call<StockDetail> getStockDetail(@Path("id") String id);
}
