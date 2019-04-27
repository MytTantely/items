package qway.myt.com.itemsearch.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import qway.myt.com.itemsearch.R;

public class FruitViewHolder extends RecyclerView.ViewHolder {
    protected TextView vTitle;
    protected TextView vName;
    protected TextView vCategory;
    protected TextView vPrice;
    protected TextView vLabel;

    public FruitViewHolder(@NonNull View itemView) {
        super(itemView);

        vTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        vName = (TextView) itemView.findViewById(R.id.txtName);
        vCategory = (TextView) itemView.findViewById(R.id.txtCategory);
        vPrice = (TextView) itemView.findViewById(R.id.txtPrice);
        vLabel = (TextView) itemView.findViewById(R.id.txtLabel);
    }
}
