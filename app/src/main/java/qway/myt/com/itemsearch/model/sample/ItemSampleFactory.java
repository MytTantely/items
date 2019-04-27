package qway.myt.com.itemsearch.model.sample;

import java.util.ArrayList;

public class ItemSampleFactory {
    private static ItemSampleFactory factory = null;
    private ArrayList<ItemSample> mItemSamples = null;
    private ArrayList<String> mList = null;

    private ItemSampleFactory(){
        mItemSamples = new ArrayList<ItemSample>();
        mList = new ArrayList<String>();
        makeItems();
    }

    public static ItemSampleFactory getInstance(){
        if (factory == null){
            factory = new ItemSampleFactory();
        }

        return factory;
    }

    public void makeItems(){
        mItemSamples.add( new ItemSample("royal gala", "apple", 4.99));
        mItemSamples.add( new ItemSample("macintosh", "apple", 5.99));
        mItemSamples.add( new ItemSample("autumn glory", "apple", 6.99));
        mItemSamples.add( new ItemSample("granny smith", "apple", 6.99));
        mItemSamples.add( new ItemSample("honeycrisp", "apple", 6.99));
        mItemSamples.add( new ItemSample("fuji", "apple", 6.99));
        mItemSamples.add( new ItemSample("red delicious", "apple", 6.99));
        mItemSamples.add( new ItemSample("cripps pink", "apple", 6.99));
        mItemSamples.add( new ItemSample("golden delicious", "apple", 6.99));
        mItemSamples.add( new ItemSample("ambrosia", "apple", 6.99));
        mItemSamples.add( new ItemSample("braeburn", "apple", 6.99));
        mItemSamples.add( new ItemSample("breeze", "apple", 6.99));

        for(ItemSample i:mItemSamples) {
            mList.add(i.getLabel());
        }
    }

    public ArrayList<ItemSample> getItems(){
        if(mItemSamples.size() == 0){
            makeItems();
        }
        return mItemSamples;
    }

    public ArrayList<String> getList(){
        return mList;
    }
}
