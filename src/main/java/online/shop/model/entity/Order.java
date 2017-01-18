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

    public Order() {
        goodsItems = new HashMap<>();
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
}