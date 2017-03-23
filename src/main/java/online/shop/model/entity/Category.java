package online.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andri on 1/1/2017.
 */
public class Category extends BaseEntity {
    private String title;

    public Category(){}

    public Category(String title) {
        this.title = title;
    }

    public Category(int id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        return getTitle().equals(category.getTitle());

    }

    @Override
    public int hashCode() {
        return getTitle().hashCode();
    }
}
