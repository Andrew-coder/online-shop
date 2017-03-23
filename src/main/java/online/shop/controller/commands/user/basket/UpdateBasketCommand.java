package online.shop.controller.commands.user.basket;

import online.shop.controller.commands.Command;
import online.shop.controller.validators.Errors;
import online.shop.model.dto.Basket;
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
        Basket basket = (Basket) request.getSession().getAttribute(Attributes.BASKET);
        Errors errors = new Errors();
        try {
            if (!basket.isEmpty()) {
                basket.getGoodsItems().entrySet()
                        .stream()
                        .forEach(g -> g.setValue(extractGoodsAmount(request, g.getKey().getId())));
            }
        }
        catch (NumberFormatException exception){
            logger.error(ErrorMessages.WRONG_BASKET_VALUES);
            errors.addError(Attributes.WRONG_BASKET_VALUES, ErrorMessages.WRONG_BASKET_VALUES);
            request.setAttribute(Attributes.ERRORS, errors);
        }
        request.getSession().setAttribute(Attributes.BASKET, basket);
        return PagesPaths.BASKET;
    }

    private int extractGoodsAmount(HttpServletRequest request, int id){
        int amount = Integer.parseInt(request.getParameter("amount"+String.valueOf(id)));
        if(amount<=0)
            throw new NumberFormatException();
        return amount;

    }
}
