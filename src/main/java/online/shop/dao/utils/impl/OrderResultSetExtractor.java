package online.shop.dao.utils.impl;

import online.shop.dao.utils.ResultSetExtractor;
import online.shop.model.entity.Order;

import java.sql.ResultSet;

/**
 * Created by andri on 1/17/2017.
 */
public class OrderResultSetExtractor implements ResultSetExtractor<Order> {
    @Override
    public Order extract(ResultSet set) {
        return null;
    }
}
