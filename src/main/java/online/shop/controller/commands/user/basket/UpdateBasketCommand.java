package online.shop.controller.commands.user.basket;

import online.shop.controller.commands.Command;
import online.shop.controller.validators.Errors;
import online.shop.model.entity.Goods;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by andri on 1/23/2017.
 */
public class UpdateBasketCommand implements Command {
    private static final Logger logger = Logger.getLogger(UpdateBasketCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Goods, Integer> goodsItems = (Map<Goods,Integer>) request.getSession().getAttribute("goods");
        Errors errors = new Errors();
        try {
            if (goodsItems != null && !goodsItems.isEmpty()) {
                goodsItems.entrySet()
                        .stream()
                        .forEach(g -> g.setValue(extractGoodsAmount(request, g.getKey().getId())));
            }
        }
        catch (NumberFormatException exception){
            logger.error(ErrorMessages.WRONG_BASKET_VALUES);
            errors.addError(Attributes.WRONG_BASKET_VALUES, ErrorMessages.WRONG_BASKET_VALUES);
            request.setAttribute(Attributes.ERRORS, errors);
        }
        request.getSession().setAttribute(Attributes.GOODS, goodsItems);
        return PagesPaths.BASKET;
    }

    private int extractGoodsAmount(HttpServletRequest request, int id){
        return Integer.parseInt(request.getParameter("amount"+String.valueOf(id)));
    }

    private long calculateTotalSum(Map<Goods, Integer> goodsItems){
        return goodsItems.entrySet()
                .stream()
                .mapToLong(item -> (long)item.getKey().getPrice()*item.getValue())
                .sum();
    }
}