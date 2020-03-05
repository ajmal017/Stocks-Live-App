package xyz.sahilsood.yumstocks.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xyz.sahilsood.yumstocks.R;

public class StockViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView name, id, price;
    ImageView imageArrow;
    OnStockClickListener onStockClickListener;

    public StockViewHolder(@NonNull View itemView, OnStockClickListener onStockClickListener) {
        super(itemView);
        this.onStockClickListener = onStockClickListener;
        id = (TextView) itemView.findViewById(R.id.textViewId);
        name = (TextView) itemView.findViewById(R.id.textViewName);
        price = (TextView) itemView.findViewById(R.id.textViewPrice);
        imageArrow = (ImageView) itemView.findViewById(R.id.imageViewArrow);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onStockClickListener.onStockClick(getAdapterPosition());
    }
}
