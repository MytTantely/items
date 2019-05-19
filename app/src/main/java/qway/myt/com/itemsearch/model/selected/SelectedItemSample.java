package qway.myt.com.itemsearch.model.selected;

import qway.myt.com.itemsearch.model.sample.ItemSample;

public class SelectedItemSample {
    private ItemSample item;
    private int quantity;
    private String unit;
    private String ref;

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
}
