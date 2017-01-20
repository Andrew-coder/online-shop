package online.shop.services.impl;

import online.shop.dao.CategoryDao;
import online.shop.dao.ConnectionWrapper;
import online.shop.dao.DaoFactory;
import online.shop.dao.SubcategoryDao;
import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;
import online.shop.services.CategoryService;

import java.util.List;

/**
 * Created by andri on 1/19/2017.
 */
public class CategoryServiceImpl implements CategoryService{
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder{
        static final CategoryService INSTANCE = new CategoryServiceImpl();
    }

    public static CategoryService getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public Category findById(int id) {
        CategoryDao categoryDao = daoFactory.getCategoryDao();
        return categoryDao.findById(id).get();
    }

    @Override
    public Category findCategoryByTitle(String title) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        try(ConnectionWrapper connection = daoFactory.getConnection()){
            CategoryDao categoryDao = daoFactory.getCategoryDao();
            SubcategoryDao subcategoryDao = daoFactory.getSubcategoryDao();
            List<Category> categories = categoryDao.findAll();
        }

    }

    @Override
    public void create(Category category) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(int id) {

    }
}
