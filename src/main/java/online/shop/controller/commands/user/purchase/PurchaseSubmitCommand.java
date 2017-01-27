package online.shop.controller.commands.user.purchase;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
import online.shop.controller.validators.Errors;
import online.shop.controller.validators.OrderValidator;
import online.shop.model.entity.Goods;
import online.shop.model.entity.Order;
import online.shop.model.entity.User;
import online.shop.services.OrderService;
import online.shop.services.UserService;
import online.shop.services.impl.OrderServiceImpl;
import online.shop.services.impl.UserServiceImpl;
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
 * Created by andri on 1/25/2017.
 */
public class PurchaseSubmitCommand extends CommandExecuter {
    private static final Logger logger = Logger.getLogger(PurchaseSubmitCommand.class);
    private OrderService orderService = OrderServiceImpl.getInstance();
    private OrderValidator orderValidator;

    public PurchaseSubmitCommand() {
        orderValidator = new OrderValidator();
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = extractOrder(request);
        String payeeType = request.getParameter(Attributes.PAYEE_TYPE).toString();
        if(!payeeType.equals(Attributes.PAY_ON_DELIVERY)){
            order.setPaid(true);
        }
        Errors errors = orderValidator.validate(order);
        if(errors.hasErrors()){
            request.setAttribute(Attributes.ERRORS, errors);
            logger.error(ErrorMessages.WRONG_ORDER_DATA);
            request.getRequestDispatcher(PagesPaths.PURCHASE_PAGE).forward(request,response);
            return PagesPaths.FORWARD;
        }
        orderService.create(order);
        request.getRequestDispatcher(PagesPaths.PURCHASE_SUCCESFULL_PAGE).forward(request, response);
        request.getSession().removeAttribute(Attributes.GOODS);
        return PagesPaths.FORWARD;
    }

    private Order extractOrder(HttpServletRequest request){
        HttpSession session = request.getSession();
        Order order = new Order();
        order.setUser((User)session.getAttribute(Attributes.USER));
        order.setGoodsItems((Map<Goods, Integer>)session.getAttribute(Attributes.GOODS));
        order.setOrderDate(new Date());
        return order;
    }
}
