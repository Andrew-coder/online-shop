package online.shop.controller.commands.user.purchase;

import online.shop.controller.commands.Command;
import online.shop.controller.validators.Errors;
import online.shop.model.dto.Basket;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Order;
import online.shop.model.entity.User;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by andri on 1/22/2017.
 */
public class PurchaseCommand implements Command {
    private static final Logger logger = Logger.getLogger(PurchaseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Basket basket = (Basket) session.getAttribute(Attributes.BASKET);
        Errors errors = new Errors();
        if(basket==null || basket.isEmpty()){
            logger.error(ErrorMessages.EMPTY_BASKET);
            response.sendRedirect(PagesPaths.BASKET);
            return PagesPaths.REDIRECT;
        }
        return PagesPaths.PURCHASE_PAGE;
    }
}
