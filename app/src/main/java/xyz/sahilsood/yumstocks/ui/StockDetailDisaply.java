package xyz.sahilsood.yumstocks.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import xyz.sahilsood.yumstocks.R;
import xyz.sahilsood.yumstocks.models.StockDetail;
import xyz.sahilsood.yumstocks.viewmodels.StocksViewModel;

public class StockDetailDisaply extends AppCompatActivity {
    private StocksViewModel stocksViewModel;
    private TextView id, name, address, website, price;
    private DecimalFormat df;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);
        df = new DecimalFormat("0.00");
        id = findViewById(R.id.tvId);
        name = findViewById(R.id.tvName);
        address = findViewById(R.id.tvAddress);
        image = findViewById(R.id.ivImage);
        price = findViewById(R.id.tvPrice);
        website = findViewById(R.id.tvWebsite);
        stocksViewModel = ViewModelProviders.of(this).get(StocksViewModel.class);
        if(getIntent()!=null || getIntent().getExtras()!=null){
            String id = getIntent().getStringExtra("id");
            String name = getIntent().getStringExtra("name");
            stocksViewModel.getStocksDetail(id);
            setTitle(name);
            subscribeObservers();
        }
    }

    public void subscribeObservers(){
        stocksViewModel.getStocks().observe(this, new Observer<StockDetail>() {
            @Override
            public void onChanged(@Nullable StockDetail stocks) {
                if(stocks!=null){
                    id.setText(stocks.id);
                    name.setText(stocks.name);
                    address.setText(stocks.address);
                    price.setText("Price: "+df.format(stocks.price));
                    website.setText(stocks.website);
                    Picasso.get().load(stocks.imageUrl).into((image));
                }
            }
        });
    }
}
