package online.shop.controller.commands.admin;

import online.shop.controller.commands.Command;
import online.shop.model.entity.Order;
import online.shop.services.OrderService;
import online.shop.services.impl.OrderServiceImpl;
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
 * Created by andri on 1/26/2017.
 */
public class UpdateOrderCommand implements Command {
    private static final Logger logger = Logger.getLogger(UpdateOrderCommand.class);
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Order> order = extractOrder(request);
        if(!order.isPresent()){
            logger.error(ErrorMessages.ORDER_NOT_FOUND);
            response.sendRedirect(PagesPaths.ORDER_ADMINISTRATION);
            return PagesPaths.REDIRECT;
        }
        request.setAttribute(Attributes.ORDER, order.get());
        return PagesPaths.ORDERS_UPDATE_PAGE;
    }

    private Optional<Order> extractOrder(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter(Attributes.ORDER_ID));
            return orderService.findById(id);
        } catch (NumberFormatException exception) {
            logger.error(ErrorMessages.WRONG_ORDER_ID);
        }
        return Optional.empty();
    }
}
