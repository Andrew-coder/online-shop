package online.shop.dao.utils.impl;

import online.shop.dao.utils.ResultSetExtractor;
import online.shop.model.entity.Goods;

import java.sql.ResultSet;

/**
 * Created by andri on 1/17/2017.
 */
public class GoodsResultSetExtractor implements ResultSetExtractor<Goods> {
    @Override
    public Goods extract(ResultSet set) {
        return null;
    }
}
