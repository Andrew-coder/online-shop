package online.shop.services;

import online.shop.model.entity.Goods;
import online.shop.model.entity.Subcategory;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/22/2017.
 */
public interface GoodsService {
    Optional<Goods> findById(int id);
    List<Goods> findGoodsByPriceRange(double minPrice, double maxPrice);
    List<Goods> findGoodsBySubcategoryId(int subcategoryId);
    List<Goods> findAll();
    void create(Goods goods);
    void update(Goods goods);
    void delete(int id);
}
