package online.shop.model.entity;

import java.util.List;

/**
 * Created by andri on 1/1/2017.
 */
public class Subcategory extends BaseEntity {
    private Category category;
    private String title;

    public Subcategory() {
    }

    public Subcategory(Category category, String title) {
        this.category = category;
        this.title = title;
    }

    public Subcategory(int id, Category category, String title) {
        super(id);
        this.category = category;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
