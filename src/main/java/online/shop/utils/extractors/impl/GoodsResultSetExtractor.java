package online.shop.utils.extractors.impl;

import online.shop.model.entity.GoodsStatus;
import online.shop.utils.extractors.ResultSetExtractor;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Subcategory;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andri on 1/17/2017.
 */
public class GoodsResultSetExtractor implements ResultSetExtractor<Goods> {
    @Override
    public Goods extract(ResultSet set) throws SQLException{
        Goods.Builder builder = new Goods.Builder()
                .setId(set.getInt("goodsID"))
                .setTitle(set.getString("title"))
                .setPrice(set.getLong("price"))
                .setDescription(set.getString("description"))
                .setSubcategory(new Subcategory(set.getInt("subcategoryID")))
                .setImage(set.getString("image"));
        String status = set.getString("availability");
        builder.setGoodsStatus(GoodsStatus.getStatus(status));
        SubcategoryResultSetExtractor subcategoryExtractor = new SubcategoryResultSetExtractor();
        builder.setSubcategory(subcategoryExtractor.extract(set));
        return builder.build();
    }
}
