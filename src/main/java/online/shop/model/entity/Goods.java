package online.shop.model.entity;

import java.util.Base64;

/**
 * Created by andri on 1/1/2017.
 */
public class Goods extends BaseEntity{
    private String title;
    private long price;
    private String description;
    private Subcategory subcategory;
    private String image;
    private GoodsStatus goodsStatus;

    public Goods() {
    }

    public Goods(String title, long price, String description, Subcategory subcategory, String image, GoodsStatus goodsStatus) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.subcategory = subcategory;
        this.image = image;
        this.goodsStatus = goodsStatus;
    }

    public Goods(int id, String title, long price, String description, Subcategory subcategory, String image, GoodsStatus goodsStatus) {
        super(id);
        this.title = title;
        this.price = price;
        this.description = description;
        this.subcategory = subcategory;
        this.image = image;
        this.goodsStatus = goodsStatus;
    }

    public Goods(String title, long price, String description, Subcategory subcategory, GoodsStatus goodsStatus) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.subcategory = subcategory;
        this.goodsStatus = goodsStatus;
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

    public void setPrice(long price) {
        this.price = price;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public GoodsStatus getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(GoodsStatus goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

        public Builder setPrice(long price){
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

        public Builder setImage(String image){
            instance.image=image;
            return this;
        }

        public Builder setGoodsStatus(GoodsStatus goodsStatus){
            instance.goodsStatus = goodsStatus;
            return this;
        }

        public Goods build(){
            return instance;
        }
    }
}
