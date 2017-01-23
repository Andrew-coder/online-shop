package online.shop.dao;

import online.shop.model.entity.Goods;
import online.shop.model.entity.Subcategory;

import java.util.List;

/**
 * Created by andri on 1/4/2017.
 */
public interface GoodsDao extends CommonDao<Goods> {
    List<Goods> findGoodsByPriceRange(double minPrice, double maxPrice);
    List<Goods> findGoodsBySubcategoryId(int subcategoryId);
}
