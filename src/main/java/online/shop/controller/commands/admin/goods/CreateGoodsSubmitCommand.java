package online.shop.controller.commands.admin.goods;

import online.shop.controller.commands.CommandExecuter;
import online.shop.controller.validators.Errors;
import online.shop.model.entity.Goods;
import online.shop.model.entity.GoodsStatus;
import online.shop.model.entity.Subcategory;
import online.shop.services.GoodsService;
import online.shop.services.SubcategoryService;
import online.shop.services.impl.GoodsServiceImpl;
import online.shop.services.impl.SubcategoryServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by andri on 1/27/2017.
 */
public class CreateGoodsSubmitCommand extends CommandExecuter {
    private static final Logger logger = Logger.getLogger(CreateGoodsSubmitCommand.class);
    private GoodsService goodsService = GoodsServiceImpl.getInstance();
    private SubcategoryService subcategoryService = SubcategoryServiceImpl.getInstance();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Errors errors = new Errors();
        Goods goods = extractGoods(request, errors);
        if(errors.hasErrors()){
            processErrors(request, errors);
            request.setAttribute(Attributes.GOODS, goods);
            request.getRequestDispatcher(PagesPaths.GOODS_CREATE_PAGE).forward(request, response);
            return PagesPaths.FORWARD;
        }
        goodsService.create(goods);
        logger.info(String.format("Goods with %d id was successfully updated",goods.getId()));
        return PagesPaths.GOODS_ADMINISTRATION;
    }

    private Goods extractGoods(HttpServletRequest request, Errors errors){
        Goods.Builder builder = new Goods.Builder()
                .setTitle(request.getParameter("title"))
                .setDescription(request.getParameter("description"))
                .setGoodsStatus(GoodsStatus.getStatus(request.getParameter("goodsStatus")));
        String subcategoryTitle = request.getParameter("subcategory");
        Optional<Subcategory> subcategory = subcategoryService.findSubcategoryByTitle(subcategoryTitle);
        if(subcategory.isPresent())
            builder.setSubcategory(subcategory.get());
        try{
            long price = Long.parseLong(request.getParameter("price"));
            builder.setPrice(price);
        }
        catch (NumberFormatException exception){
            errors.addError(Attributes.ERROR, ErrorMessages.WRONG_PRICE);
        }
        return builder.build();
    }

    private void processErrors(HttpServletRequest request, Errors errors){
        logger.error(ErrorMessages.WRONG_GOODS_DATA);
        request.setAttribute(Attributes.ERRORS, errors);
    }
}
