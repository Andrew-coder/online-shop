package online.shop.controller.commands.admin;

import online.shop.controller.commands.Command;
import online.shop.model.entity.RoleType;
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

/**
 * Created by andri on 1/26/2017.
 */
public class UpdateOrderSubmitCommand implements Command {
    private static final Logger logger = Logger.getLogger(UpdateOrderSubmitCommand.class);
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int orderID = Integer.parseInt(request.getParameter(Attributes.ORDER_ID));
            boolean orderStatus = extractBooleanValueFromOrderStatus(request);
            orderService.updateOrderStatus(orderID, orderStatus);
        }
        catch (NumberFormatException exception){
            logger.error(ErrorMessages.WRONG_ORDER_ID);
        }
        return PagesPaths.ORDER_ADMINISTRATION;
    }

    private boolean extractBooleanValueFromOrderStatus(HttpServletRequest request){
        String orderStatus = request.getParameter(Attributes.ORDER_STATUS);
        if(Attributes.ORDER_PAID.equals(orderStatus)){
            return true;
        }
        return false;
    }
}
