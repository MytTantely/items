package qway.myt.com.itemsearch.model.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import java.util.ArrayList;

import qway.myt.com.itemsearch.R;

public class ItemSampleAdapter extends ArrayAdapter<ItemSample> {

    // View lookup cache
    public static class ViewHolder {
        public TextView name;
        public TextView category;
        public TextView price;
    }

    public ItemSampleAdapter(@NonNull Context context, ArrayList<ItemSample> aItemSamples) {
        super(context, 0, aItemSamples);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data Item for this position
        final ItemSample itemSample = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ItemSampleAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ItemSampleAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.details_item, parent, false);
//            viewHolder.name = (TextView) convertView.findViewById(R.id.ivBookCover);
            viewHolder.category = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tvAuthor);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ItemSampleAdapter.ViewHolder) convertView.getTag();
        }

// Populate the data into the template view using the data object
        viewHolder.category.setText(itemSample.getLabel());
        viewHolder.price.setText(itemSample.getPrice().toString());

        return convertView;
    }
}
