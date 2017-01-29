package online.shop.model.dto;

import online.shop.model.entity.Goods;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andri on 1/29/2017.
 */
public class Basket {
    private Map<Goods,Integer> goodsItems = new HashMap<>();

    public Map<Goods, Integer> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(Map<Goods, Integer> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public void addGoodsItem(Goods goods, int amount){
        goodsItems.put(goods, amount);
    }

    public void removeGoodsItem(Goods goods){
        goodsItems.remove(goods);
    }

    public boolean isEmpty(){
        return goodsItems.isEmpty();
    }

    public double getTotalPrice(){
        return goodsItems.entrySet()
                    .stream()
                    .mapToDouble(entry -> entry.getKey().getRealPrice()*entry.getValue())
                    .sum();
    }

    public boolean contains(Goods goods){
        return goodsItems.keySet().contains(goods);
    }
}
