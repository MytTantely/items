package qway.myt.com.itemsearch.model.sample;

public class ItemSample {
    private String name;
    private String category;
    private Double price;

    public String getLabel() {
        return category + " - " + name;
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
    }

    public ItemSample (String name, String category, Double price, Sale onSale){
        this.name = name;
        this.category = category;
        this.price = price;
        this.onSale = onSale;
    }
}
