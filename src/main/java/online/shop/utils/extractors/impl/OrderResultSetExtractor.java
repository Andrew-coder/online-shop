package online.shop.utils.extractors.impl;

import online.shop.utils.extractors.ResultSetExtractor;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andri on 1/17/2017.
 */
public class OrderResultSetExtractor implements ResultSetExtractor<Order> {
    @Override
    public Order extract(ResultSet set) throws SQLException {
        Order.Builder builder = new Order.Builder()
                                .setId(set.getInt("orderID"))
                                .setOrderDate(set.getDate("orderDate"))
                                .setPaid(set.getBoolean("paid"))
                                .setTotalPrice(set.getLong("totalPrice"));
        UserResultSetExtractor userExtractor = new UserResultSetExtractor();
        builder.setUser(userExtractor.extract(set));
        builder.setGoodsItems(extractGoodsItems(set));
        return builder.build();
    }

    public Map<Goods, Integer> extractGoodsItems(ResultSet set) throws SQLException {
        Map<Goods, Integer> goodsItems = new HashMap<>();
        GoodsResultSetExtractor goodsExtractor = new GoodsResultSetExtractor();
        int orderId = set.getInt("orderID");
        while(set.next() && set.getInt("orderID")==orderId){
            Goods goods = goodsExtractor.extract(set);
            int amount = set.getInt("amount");
            goodsItems.put(goods, amount);
        }
        return goodsItems;
    }
}
