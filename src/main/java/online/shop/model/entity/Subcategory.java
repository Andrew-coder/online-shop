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

    public Subcategory(int id) {
        super(id);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subcategory)) return false;

        Subcategory that = (Subcategory) o;

        return getTitle().equals(that.getTitle());

    }

    @Override
    public int hashCode() {
        return getTitle().hashCode();
    }
}
