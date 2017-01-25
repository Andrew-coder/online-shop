package online.shop.model.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andri on 1/3/2017.
 */
public class Order extends BaseEntity{
    private User user;
    private Map<Goods , Integer>  goodsItems;
    private Date orderDate;
    private boolean paid;
    private long totalPrice;

    public Order() {
        goodsItems = new HashMap<>();
        paid=false;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isPaid(){
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void addGoods(Goods goods, int amount){
        goodsItems.put(goods, amount);
    }

    public User getUser() {
        return user;
    }

    public Map<Goods, Integer> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(Map<Goods, Integer> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static class Builder{
        Order instance = new Order();

        public Builder setId(int id){
            instance.setId(id);
            return this;
        }

        public Builder setUser(User user){
            instance.user = user;
            return this;
        }

        public Builder setGoodsItems(Map<Goods, Integer> goodsItems){
            instance.goodsItems = goodsItems;
            return this;
        }

        public Builder setOrderDate(Date orderDate){
            instance.orderDate = orderDate;
            return this;
        }

        public Builder setPaid(boolean paid){
            instance.paid = paid;
            return this;
        }

        public Builder setTotalPrice(long totalPrice){
            instance.totalPrice = totalPrice;
            return this;
        }

        public Order build(){
            return instance;
        }
    }
}