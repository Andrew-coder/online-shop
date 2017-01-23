package online.shop.services.impl;

import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.SubcategoryDao;
import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;
import online.shop.services.SubcategoryService;

import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/22/2017.
 */
public class SubcategoryServiceImpl implements SubcategoryService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static SubcategoryService instance;

    private SubcategoryServiceImpl(){}

    public static synchronized SubcategoryService getInstance(){
        if(instance==null){
            instance = new SubcategoryServiceImpl();
        }
        return instance;
    }
    @Override
    public Optional<Subcategory> findById(int id) {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            SubcategoryDao subcategoryDao = daoFactory.getSubcategoryDao(connection);
            return subcategoryDao.findById(id);
        }
    }

    @Override
    public Optional<Subcategory> findSubcategoryByTitle(String title) {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            SubcategoryDao subcategoryDao = daoFactory.getSubcategoryDao(connection);
            return subcategoryDao.findSubcategoryByTitle(title);
        }
    }

    @Override
    public List<Subcategory> findSubcategoriesByCategoryId(int categoryId) {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            SubcategoryDao subcategoryDao = daoFactory.getSubcategoryDao(connection);
            return subcategoryDao.findSubcategoriesByCategoryId(categoryId);
        }
    }

    @Override
    public List<Subcategory> findAll() {
        try(ConnectionWrapper connection = daoFactory.getConnection()){
            SubcategoryDao subcategoryDao = daoFactory.getSubcategoryDao(connection);
            return subcategoryDao.findAll();
        }
    }

    @Override
    public void create(Subcategory subcategory) {

    }

    @Override
    public void update(Subcategory subcategory) {

    }

    @Override
    public void delete(Subcategory subcategory) {

    }
}
