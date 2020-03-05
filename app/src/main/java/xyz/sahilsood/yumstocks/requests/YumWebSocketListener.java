package xyz.sahilsood.yumstocks.requests;


import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import xyz.sahilsood.yumstocks.ui.StockListDisplay;
import xyz.sahilsood.yumstocks.models.StockTicker;

public class YumWebSocketListener extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private static YumWebSocketListener instance;
    private static final OkHttpClient client = new OkHttpClient();
    public MutableLiveData<List<StockTicker>> mStocks = new MutableLiveData<>();
    List<StockTicker> lcs;

    private YumWebSocketListener(){
    }

    public static YumWebSocketListener getInstance(){
        if(instance == null){
            instance = new YumWebSocketListener();
        }
        return instance;
    }

    public LiveData<List<StockTicker>> getStocks(){
        return mStocks;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
    }
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        //output(text);
        Type collectionType = new TypeToken<List<StockTicker>>(){}.getType();
        lcs = (List<StockTicker>) new Gson()
                .fromJson( text , collectionType);
        output(lcs);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.println("1");
    }
    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
    }
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.out.println(t);
    }

    public static void start() {
        Request request = new Request.Builder().url("ws://interviews.yum.dev/ws/stocks").build();
        YumWebSocketListener listener = new YumWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    private void output(final List<StockTicker> txt) {
        Handler h = new Handler(Looper.getMainLooper());
        if(!StockListDisplay.filterSearch) {
            h.post(new Runnable() {
                public void run() {
                    mStocks.postValue(txt);
                    StockListDisplay.mAdapter.setStocks(txt);
                    StockListDisplay.progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}