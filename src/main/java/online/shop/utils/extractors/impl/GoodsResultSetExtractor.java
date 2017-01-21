package online.shop.utils.extractors.impl;

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
        Blob blob = set.getBlob("image");
        Goods goods = new Goods.Builder()
                .setTitle(set.getString("title"))
                .setPrice(set.getDouble("price"))
                .setDescription(set.getString("description"))
                .setEnds(set.getBoolean("ends"))
                .setImage(blob.getBytes(1, (int)blob.length()))
                .setSubcategory(new Subcategory(set.getInt("subcategoryID")))
                .build();
        SubcategoryResultSetExtractor subcategoryExtractor = new SubcategoryResultSetExtractor();
        goods.setSubcategory(subcategoryExtractor.extract(set));
        return goods;
    }
}
