package online.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andri on 1/1/2017.
 */
public class Category extends BaseEntity {
    private String title;
    private List<Subcategory> subcategories;

    public Category() {
        subcategories = new ArrayList<>();
    }

    public Category(String title, List<Subcategory> subcategories) {
        this.title = title;
        this.subcategories = subcategories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
