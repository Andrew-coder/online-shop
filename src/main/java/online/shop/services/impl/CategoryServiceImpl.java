package online.shop.services.impl;

import online.shop.dao.CategoryDao;
import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.SubcategoryDao;
import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;
import online.shop.services.CategoryService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by andri on 1/19/2017.
 */
public class CategoryServiceImpl implements CategoryService{
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private CategoryServiceImpl(){}

    private static CategoryService instance;

    public static synchronized  CategoryService getInstance(){
        if(instance==null){
            instance = new CategoryServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<Category> findById(int id) {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            CategoryDao categoryDao = daoFactory.getCategoryDao(connection);
            return categoryDao.findById(id);
        }
    }

    @Override
    public Optional<Category> findCategoryByTitle(String title) {
        try(ConnectionWrapper connection = daoFactory.getConnection()) {
            CategoryDao categoryDao = daoFactory.getCategoryDao(connection);
            return categoryDao.findCategoryByTitle(title);
        }
    }

    @Override
    public List<Category> findAll() {
        try(ConnectionWrapper connection = daoFactory.getConnection()){
            CategoryDao categoryDao = daoFactory.getCategoryDao(connection);
            return categoryDao.findAll();
        }

    }

    @Override
    public void create(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}
