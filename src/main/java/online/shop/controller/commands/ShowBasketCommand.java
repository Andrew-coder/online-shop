package online.shop.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static online.shop.utils.constants.PagesPaths.BASKET_PAGE;

/**
 * Created by andri on 1/22/2017.
 */
public class ShowBasketCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return BASKET_PAGE;
    }
}
