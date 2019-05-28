package qway.myt.com.itemsearch.model.sample;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemSample implements Parcelable {
    private String name;
    private String category;
    private Double price;
    private String label;

    public static final Creator<ItemSample> CREATOR = new Creator<ItemSample>() {
        @Override
        public ItemSample createFromParcel(Parcel in) {
            return new ItemSample(in);
        }

        @Override
        public ItemSample[] newArray(int size) {
            return new ItemSample[size];
        }
    };

    private void setLabel(String category, String name){
        this.label = category + " - " + name;
    }

    public String getLabel() {
        return this.label;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Sale getOnSale() {
        return onSale;
    }

    public void setOnSale(Sale onSale) {
        this.onSale = onSale;
    }

    private Sale onSale;

    public ItemSample (String name, String category, Double price){
        this.name = name;
        this.category = category;
        this.price = price;
        this.onSale = null;
        this.setLabel(this.category, this.name);
    }

    public ItemSample (String name, String category, Double price, Sale onSale){
        this.name = name;
        this.category = category;
        this.price = price;
        this.onSale = onSale;
        this.setLabel(this.category, this.name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
        dest.writeString(label);
    }

    // Parcelling part
    public ItemSample(Parcel in){
        this.name = in.readString();
        this.category = in.readString();
        this.price = in.readDouble();
        this.label = in.readString();
    }
}
