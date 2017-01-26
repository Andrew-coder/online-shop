package online.shop.controller.commands.admin;

import online.shop.controller.commands.Command;
import online.shop.model.entity.Order;
import online.shop.services.OrderService;
import online.shop.services.impl.OrderServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by andri on 1/26/2017.
 */
public class OrdersAdministrationCommand implements Command{
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.findAll();
        request.setAttribute(Attributes.ORDERS, orders);
        return PagesPaths.ORDER_ADMINISTRATION_PAGE;
    }
}
