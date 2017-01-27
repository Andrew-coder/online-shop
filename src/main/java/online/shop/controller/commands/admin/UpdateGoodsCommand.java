package online.shop.controller.commands.admin;

import online.shop.controller.commands.Command;
import online.shop.controller.validators.Errors;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Subcategory;
import online.shop.services.GoodsService;
import online.shop.services.SubcategoryService;
import online.shop.services.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import online.shop.services.impl.SubcategoryServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

/**
 * Created by andri on 1/24/2017.
 */
public class UpdateGoodsCommand implements Command {
    private static final Logger logger = Logger.getLogger(UpdateGoodsCommand.class);
    private SubcategoryService subcategoryService = SubcategoryServiceImpl.getInstance();
    private GoodsService goodsServices = GoodsServiceImpl.getInstance();
    private static final String WRONG_GOODS_ID = "wrong goods id parameter from request";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Goods> goods = extractGoods(request);
        Errors errors = new Errors();
        if(!goods.isPresent()){
            logger.error(ErrorMessages.GOODS_NOT_FOUND);
            response.sendRedirect(PagesPaths.GOODS_ADMINISTRATION);
            return PagesPaths.REDIRECT;
        }
        List<Subcategory> subcategories = subcategoryService.findAll();
        if(subcategories.isEmpty()){
            errors.addError(Attributes.SUBCATEGORIES, ErrorMessages.SUBCATEGORIES_NOT_FOUND);
        }
        request.setAttribute(Attributes.GOODS, goods.get());
        request.setAttribute(Attributes.SUBCATEGORIES, subcategories);
        request.setAttribute(Attributes.ERRORS, errors);
        return PagesPaths.GOODS_UPDATE_PAGE;
    }

    private Optional<Goods> extractGoods(HttpServletRequest request){
        try {
            int id = Integer.parseInt(request.getParameter(Attributes.GOODS_ID));
            return goodsServices.findById(id);
        }
        catch (NumberFormatException exception){
            logger.error(WRONG_GOODS_ID);
        }
        return Optional.empty();
    }
}
