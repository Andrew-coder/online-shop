package online.shop.model.entity;

import java.util.Base64;

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

    public String getImageInBase64(){
        byte[] decoded = Base64.getEncoder().encode(image);
        return new String(decoded);
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Goods)) return false;

        Goods goods = (Goods) o;

        if (Double.compare(goods.getPrice(), getPrice()) != 0) return false;
        if (isEnds() != goods.isEnds()) return false;
        if (!getTitle().equals(goods.getTitle())) return false;
        return getDescription().equals(goods.getDescription());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getTitle().hashCode();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + (isEnds() ? 1 : 0);
        return result;
    }

    public static class Builder{
        Goods instance = new Goods();

        public Builder setId(int id){
            instance.setId(id);
            return this;
        }

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
