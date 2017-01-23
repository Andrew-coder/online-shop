package online.shop.controller.commands;

import online.shop.model.entity.Category;
import online.shop.services.CategoryService;
import online.shop.services.impl.CategoryServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static online.shop.utils.constants.PagesPaths.HOME_PAGE;

/**
 * Created by andri on 1/20/2017.
 */
public class HomeCommand implements Command {
    private CategoryService categoryService = CategoryServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute(Attributes.CATEGORIES, categories);
        return HOME_PAGE;
    }
}
