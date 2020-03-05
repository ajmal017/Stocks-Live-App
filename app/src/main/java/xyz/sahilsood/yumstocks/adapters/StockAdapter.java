package xyz.sahilsood.yumstocks.adapters;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xyz.sahilsood.yumstocks.R;
import xyz.sahilsood.yumstocks.models.StockTicker;
import xyz.sahilsood.yumstocks.ui.StockListDisplay;

public class StockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    List<StockTicker> mData = new ArrayList<>();
    List<StockTicker> mDataFull;
    private OnStockClickListener onStockClickListener;
    private double[] previousPrice=new double[10];

    public StockAdapter(OnStockClickListener onStockClickListener) {
        this.onStockClickListener = onStockClickListener;
        Arrays.fill(previousPrice, 0);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item,parent,false);
        return new StockViewHolder(view, onStockClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StockTicker stocks = mData.get(position);
        DecimalFormat df = new DecimalFormat("0.00");
        ((StockViewHolder)holder).name.setText(stocks.getName());
        ((StockViewHolder)holder).id.setText(stocks.getId());
        double price = stocks.getPrice();
        double prev = previousPrice[position];
        ((StockViewHolder)holder).price.setText(df.format(price));
        if(price<prev && prev!=0){
            ((StockViewHolder)holder).price.setText(df.format(price));
//            ((StockViewHolder)holder).price.setTextColor(Color.parseColor("#ee161e"));
            ((StockViewHolder)holder).imageArrow.setImageResource(R.drawable.ic_arrow_red);
            previousPrice[position] = price;
        }
        else if(price>prev && prev!=0){
            ((StockViewHolder)holder).price.setText(df.format(price));
//            ((StockViewHolder)holder).price.setTextColor(Color.parseColor("#32CD32"));
            ((StockViewHolder)holder).imageArrow.setImageResource(R.drawable.ic_arrow_green);
            previousPrice[position] = price;
        }
        else{
//            ((StockViewHolder)holder).price.setTextColor(Color.parseColor("#32CD32"));
            previousPrice[position] = price;
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewName);
        }
    }

    public void setStocks(List<StockTicker> stockTickers){
        if(stockTickers!=null){
            mData = stockTickers;
            mDataFull = new ArrayList<>(mData);
            notifyDataSetChanged();
        }
        else{
            int size = mData.size();
            mData.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    public StockTicker getSelectedStock(int position){
        if(mData!=null){
            if(mData.size()>0){
                return mData.get(position);
            }
        }
        return null;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<StockTicker> filteredList = new ArrayList<>();

            if(charSequence==null || charSequence.length()==0){
                mDataFull.addAll(filteredList);
            }
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(StockTicker stockTicker : mDataFull){
                    for (String name : stockTicker.companyType) {
                        if (name.toLowerCase().contains(filterPattern)) {
                            filteredList.add(stockTicker);
                        }
                    }
                    if(stockTicker.name.toLowerCase().startsWith(filterPattern)){
                        filteredList.add(stockTicker);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if(mData!=null && filterResults.values!=null){
                mData.clear();
                mData.addAll((List)filterResults.values);
                notifyDataSetChanged();
            }
        }
    };
}