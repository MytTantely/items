package qway.myt.com.itemsearch.model.selected;

import android.os.Parcel;
import android.os.Parcelable;

import qway.myt.com.itemsearch.model.sample.ItemSample;

public class SelectedItemSample implements Parcelable {
    private ItemSample item;
    private int quantity;
    private String unit;
    private String ref;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SelectedItemSample createFromParcel(Parcel in) {
            return new SelectedItemSample(in);
        }

        public SelectedItemSample[] newArray(int size) {
            return new SelectedItemSample[size];
        }
    };

    // Parcelling part
    public SelectedItemSample(Parcel in){
        this.item = in.readParcelable(ItemSample.class.getClassLoader());
        this.quantity = in.readInt();
        this.unit =  in.readString();
        this.ref = in.readString();
    }

    public ItemSample getItem() {
        return item;
    }

    public void setItem(ItemSample item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public SelectedItemSample(String ref, ItemSample item, int qty, String unit){
        this.ref = ref;
        this.item = item;
        this.quantity = qty;
        this.unit = unit;
    }

    public SelectedItemSample(ItemSample item, int qty, String unit){
        this.item = item;
        this.quantity = qty;
        this.unit = unit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
