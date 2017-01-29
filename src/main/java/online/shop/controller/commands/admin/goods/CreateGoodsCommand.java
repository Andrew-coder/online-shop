package online.shop.controller.commands.admin.goods;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
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
 * Created by andri on 1/27/2017.
 */
public class CreateGoodsCommand extends CommandExecuter {
    private SubcategoryService subcategoryService = SubcategoryServiceImpl.getInstance();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subcategory> subcategories = subcategoryService.findAll();
        request.setAttribute(Attributes.SUBCATEGORIES, subcategories);
        return PagesPaths.GOODS_CREATE_PAGE;
    }
}
