package online.shop.dao.utils.impl;

import online.shop.dao.utils.ResultSetExtractor;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Order;
import online.shop.model.entity.Subcategory;

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
        Order order = new Order();
        order.setId(set.getInt("orderID"));
        order.setOrderDate(set.getDate("orderDate"));
        UserResultSetExtractor userExtractor = new UserResultSetExtractor();
        order.setUser(userExtractor.extract(set));
        return order;
    }

    public Map<Goods, Integer> extractGoodsItems(ResultSet set) throws SQLException {
        Map<Goods, Integer> goodsItems = new HashMap<>();
        GoodsResultSetExtractor goodsExtractor = new GoodsResultSetExtractor();
        SubcategoryResultSetExtractor subcategoryExtractor = new SubcategoryResultSetExtractor();
        while(set.next()){
            Goods goods = goodsExtractor.extract(set);
            goods.setSubcategory(subcategoryExtractor.extract(set));
            int amount = set.getInt("amount");
            goodsItems.put(goods, amount);
        }
        return goodsItems;
    }
}
