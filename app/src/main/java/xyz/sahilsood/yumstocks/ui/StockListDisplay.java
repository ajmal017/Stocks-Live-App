package xyz.sahilsood.yumstocks.ui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;


import xyz.sahilsood.yumstocks.R;
import xyz.sahilsood.yumstocks.adapters.OnStockClickListener;
import xyz.sahilsood.yumstocks.adapters.StockAdapter;
import xyz.sahilsood.yumstocks.models.StockTicker;
import xyz.sahilsood.yumstocks.viewmodels.StocksViewModel;

public class StockListDisplay extends AppCompatActivity implements OnStockClickListener {
    private RecyclerView mRecyclerView;
    public static StockAdapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private StocksViewModel stocksViewModel;
    public static ProgressBar progressBar;
    public static boolean filterSearch;
    private List<StockTicker> stocksList;
    private AlertDialog.Builder builder;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        progressBar = findViewById(R.id.progressBar);
        filterSearch = false;
        setTitle("Yum! Stocks");
        stocksViewModel = ViewModelProviders.of(this).get(StocksViewModel.class);
        stocksList = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        initRecyclerView();
        getStocksList();
        builder = new AlertDialog.Builder(this);
    }

    private void getStocksList(){
        stocksViewModel.getStocksList();
    }

    private void initRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlayoutManager);
        mAdapter = new StockAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStockClick(int position) {
        StockTicker p = mAdapter.getSelectedStock(position);
        Intent intent = new Intent(StockListDisplay.this, StockDetailDisaply.class);
        intent.putExtra("id", p.id);
        intent.putExtra("name", p.name);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.yum_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE );
        searchView.setQueryHint("Filter by prefix..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterSearch = true;
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    mAdapter.setStocks(null);
                    filterSearch = false;
                    getStocksList();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CharSequence[] colors = {"Retail","Tech","Healthcare","Auto","Food"};
        builder.setTitle("Filter by Company Type").setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                filterSearch = true;
                mAdapter.getFilter().filter(colors[i].toString());
            }
        }).setPositiveButton("Clear Filter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mAdapter.setStocks(null);
                filterSearch = false;
                getStocksList();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        alert = builder.create();
        int id = item.getItemId();
        if (id == R.id.filter_category) {
            alert.show();
        }
        return true;
    }
}
