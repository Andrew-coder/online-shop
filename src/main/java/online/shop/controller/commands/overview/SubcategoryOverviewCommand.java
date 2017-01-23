package online.shop.controller.commands.overview;

import online.shop.controller.commands.Command;
import online.shop.dao.SubcategoryDao;
import online.shop.dao.jdbc.SubcategoryDaoImpl;
import online.shop.model.entity.Subcategory;
import online.shop.services.SubcategoryService;
import online.shop.services.impl.SubcategoryServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by andri on 1/22/2017.
 */
public class SubcategoryOverviewCommand implements Command {
    private SubcategoryService subcategoryService = SubcategoryServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(Attributes.CATEGORY_ID));
        List<Subcategory> subcategories = subcategoryService.findSubcategoriesByCategoryId(id);
        request.setAttribute(Attributes.SUBCATEGORIES, subcategories);
        return PagesPaths.SUBCATEGORY_PAGE;
    }
}
