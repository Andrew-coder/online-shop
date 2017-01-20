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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
