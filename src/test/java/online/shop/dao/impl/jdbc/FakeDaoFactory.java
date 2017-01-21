package online.shop.dao.impl.jdbc;

import online.shop.dao.*;
import online.shop.model.entity.Category;
import online.shop.model.entity.Subcategory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by andri on 1/21/2017.
 */
public class FakeDaoFactory extends DaoFactory {
    @Override
    public ConnectionWrapper getConnection() {
        return null;
    }

    @Override
    public CategoryDao getCategoryDao(ConnectionWrapper wrapper) {
        return new CategoryDao() {
            List<Category> db= Arrays.asList(   new Category(1, "first"),
                                                new Category(2,"second"),
                                                new Category(3, "third"));

            @Override
            public Optional<Category> findCategoryByTitle(String title) {
                return db.stream()
                        .filter(category -> category.getTitle().equals(title))
                        .findFirst();
            }

            @Override
            public Optional<Category> findById(int id) {
                return db.stream()
                        .filter(category -> category.getId()==id)
                        .findFirst();
            }

            @Override
            public List<Category> findAll() {
                return db;
            }

            @Override
            public void create(Category category) {
                db.add(category);
            }

            @Override
            public void update(Category category) {
                db.stream().filter( c -> c.getId() == category.getId()).findAny().ifPresent(
                        c -> c.setTitle( category.getTitle()));
            }

            @Override
            public void delete(int id) {
                db = db.stream().filter( c -> c.getId() != id ).collect(Collectors.toList());
            }
        };
    }

    @Override
    public SubcategoryDao getSubcategoryDao(ConnectionWrapper wrapper) {
        return new SubcategoryDao() {
            //List<Subcategory> db = Arrays.asList(1,"first subcategory");
            @Override
            public Optional<Subcategory> findSubcategoryByTitle(String title) {
                return null;
            }

            @Override
            public List<Subcategory> findSubcategoriesByCategory(Category category) {
                return null;
            }

            @Override
            public Optional<Subcategory> findById(int id) {
                return null;
            }

            @Override
            public List<Subcategory> findAll() {
                return null;
            }

            @Override
            public void create(Subcategory subcategory) {

            }

            @Override
            public void update(Subcategory subcategory) {

            }

            @Override
            public void delete(int id) {

            }
        };
    }

    @Override
    public UserDao getUserDao(ConnectionWrapper wrapper) {
        return null;
    }

    @Override
    public OrderDao getOrderDao(ConnectionWrapper wrapper) {
        return null;
    }

    @Override
    public GoodsDao getGoodsDao(ConnectionWrapper wrapper) {
        return null;
    }
}
