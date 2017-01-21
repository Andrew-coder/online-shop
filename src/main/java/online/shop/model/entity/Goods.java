package online.shop.model.entity;

/**
 * Created by andri on 1/1/2017.
 */
public class Goods extends BaseEntity{
    private String title;
    private double price;
    private String description;
    private Subcategory subcategory;
    private byte[] image;
    private boolean ends;

    public Goods() {
    }

    public Goods(String title, double price, String description, Subcategory subcategory, byte[] image, boolean ends) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.subcategory = subcategory;
        this.image = image;
        this.ends = ends;
    }

    public Goods(int id, String title, double price, String description, Subcategory subcategory, byte[] image, boolean ends) {
        super(id);
        this.title = title;
        this.price = price;
        this.description = description;
        this.subcategory = subcategory;
        this.image = image;
        this.ends = ends;
    }

    public Goods(String title, double price, String description, Subcategory subcategory, boolean ends) {
        this.title = title;
        this.price = price;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public static class Builder{
        Goods instance = new Goods();

        public Builder setTitle(String title){
            instance.title = title;
            return this;
        }

        public Builder setPrice(double price){
            instance.price = price;
            return this;
        }

        public Builder setDescription(String description){
            instance.description = description;
            return this;
        }

        public Builder setSubcategory(Subcategory subcategory){
            instance.subcategory = subcategory;
            return this;
        }

        public Builder setImage(byte[] image){
            instance.image=image;
            return this;
        }

        public Builder setEnds(boolean ends){
            instance.ends = ends;
            return this;
        }

        public Goods build(){
            return instance;
        }
    }
}
