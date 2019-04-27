package qway.myt.com.itemsearch.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import qway.myt.com.itemsearch.R;
import qway.myt.com.itemsearch.model.sample.ItemSample;

public class FruitAdapter extends RecyclerView.Adapter<FruitViewHolder> {

    private List<ItemSample> fruitList;

    public FruitAdapter(List<ItemSample> fruits){
        this.fruitList = fruits;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rowcard, viewGroup, false);
        return new FruitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder fruitViewHolder, int i) {
        ItemSample fruit = fruitList.get(i);

        fruitViewHolder.vName.setText(fruit.getName());
        fruitViewHolder.vLabel.setText(fruit.getLabel());
        fruitViewHolder.vTitle.setText(fruit.getLabel());
        fruitViewHolder.vCategory.setText(fruit.getCategory());
        fruitViewHolder.vPrice.setText(fruit.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
