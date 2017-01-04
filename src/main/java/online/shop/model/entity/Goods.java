package online.shop.model.entity;

/**
 * Created by andri on 1/1/2017.
 */
public class Goods extends BaseEntity{
    private String title;
    private double price;
    private Subcategory subcategory;
    private boolean ends;

    public Goods() {
    }

    public Goods(String title, double price, Subcategory subcategory, boolean ends) {
        this.title = title;
        this.price = price;
        this.subcategory = subcategory;
        this.ends = ends;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isEnds() {
        return ends;
    }

    public void setEnds(boolean ends) {
        this.ends = ends;
    }

    public static class Builder{
        Goods instance = new Goods();

        public Goods setTitle(String title){
            instance.title = title;
            return instance;
        }

        public Goods setPrice(double price){
            instance.price = price;
            return instance;
        }

        public Goods setSubcategory(Subcategory subcategory){
            instance.subcategory = subcategory;
            return instance;
        }

        public Goods setEnds(boolean ends){
            instance.ends = ends;
            return instance;
        }

        public Goods build(){
            return instance;
        }
    }
}
