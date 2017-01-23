package online.shop.services.impl;

import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.GoodsDao;
import online.shop.model.entity.Goods;
import online.shop.services.GoodsService;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/22/2017.
 */
public class GoodsServiceImpl implements GoodsService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static GoodsService instance;

    private GoodsServiceImpl(){}

    public static synchronized GoodsService getInstance(){
        if(instance==null){
            instance = new GoodsServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<Goods> findById(int id) {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            GoodsDao goodsDao = daoFactory.getGoodsDao(connection);
            return goodsDao.findById(id);
        }
    }

    @Override
    public List<Goods> findGoodsByPriceRange(double minPrice, double maxPrice) {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            GoodsDao goodsDao = daoFactory.getGoodsDao(connection);
            return goodsDao.findGoodsByPriceRange(minPrice, maxPrice);
        }
    }

    @Override
    public List<Goods> findGoodsBySubcategoryId(int subcategoryId) {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            GoodsDao goodsDao = daoFactory.getGoodsDao(connection);
            return goodsDao.findGoodsBySubcategoryId(subcategoryId);
        }
    }

    @Override
    public List<Goods> findAll() {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            GoodsDao goodsDao = daoFactory.getGoodsDao(connection);
            return goodsDao.findAll();
        }
    }

    @Override
    public void create(Goods goods) {

    }

    @Override
    public void update(Goods goods) {

    }

    @Override
    public void delete(int id) {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            GoodsDao goodsDao = daoFactory.getGoodsDao(connection);
            goodsDao.delete(id);
        }
    }
}
