package online.shop.model.entity;

import java.util.List;

/**
 * Created by andri on 1/1/2017.
 */
public class Subcategory extends BaseEntity {
    private String title;

    public Subcategory() {
    }

    public Subcategory(int id){
        super(id);
    }

    public Subcategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
