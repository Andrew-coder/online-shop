package online.shop.controller;

import online.shop.controller.commands.*;
import online.shop.controller.commands.admin.*;
import online.shop.controller.commands.admin.blacklist.AddBlacklistCommand;
import online.shop.controller.commands.admin.blacklist.RemoveBlacklistCommand;
import online.shop.controller.commands.user.purchase.PurchaseCommand;
import online.shop.controller.commands.user.basket.UpdateBasketCommand;
import online.shop.controller.commands.user.purchase.PurchaseSubmitCommand;
import online.shop.controller.commands.user.register.RegisterCommand;
import online.shop.controller.commands.user.register.RegisterSubmitCommand;
import online.shop.controller.commands.login.LoginCommand;
import online.shop.controller.commands.login.LoginSubmitCommand;
import online.shop.controller.commands.overview.GoodsOverviewCommand;
import online.shop.controller.commands.overview.SubcategoryOverviewCommand;
import online.shop.controller.commands.user.basket.AddBasketCommand;
import online.shop.controller.commands.user.basket.RemoveBasketCommand;
import online.shop.controller.commands.user.basket.ShowBasketCommand;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andri on 1/19/2017.
 */
public class MainController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MainController.class);
    private static final long serialVersionUID = 1L;

    private Map<String , Command> commands = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = processRequest(request, response);
        if(!path.equals(PagesPaths.REDIRECT)) {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = processRequest(request, response);
        if(!path.equals(PagesPaths.FORWARD))
            response.sendRedirect(path);
    }

    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String method = request.getMethod().toUpperCase();
            String path = request.getRequestURI();
            String key = method+":"+path;
            Command command = commands.getOrDefault(key, new PageNotFoundCommand());
            return command.execute(request, response);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            return PagesPaths.ERROR_PAGE;
        }
    }

    @Override
    public void init() throws ServletException {
        commands.put("GET:/online-shop/", new HomeCommand());
        commands.put("GET:/online-shop/login", new LoginCommand());
        commands.put("POST:/online-shop/login", new LoginSubmitCommand());
        commands.put("GET:/online-shop/logout", new LogOutCommand());
        commands.put("GET:/online-shop/basket", new ShowBasketCommand());
        commands.put("GET:/online-shop/basket/add", new AddBasketCommand());
        commands.put("GET:/online-shop/basket/remove", new RemoveBasketCommand());
        commands.put("POST:/online-shop/update", new UpdateBasketCommand());
        commands.put("GET:/online-shop/register", new RegisterCommand());
        commands.put("POST:/online-shop/register", new RegisterSubmitCommand());
        commands.put("GET:/online-shop/subcategory", new SubcategoryOverviewCommand());
        commands.put("GET:/online-shop/goods", new GoodsOverviewCommand());
        commands.put("GET:/online-shop/purchase", new PurchaseCommand());
        commands.put("POST:/online-shop/purchase", new PurchaseSubmitCommand());
        commands.put("GET:/online-shop/admin", new AdminHomeCommand());
        commands.put("GET:/online-shop/admin/goods", new GoodsAdministrationCommand());
        commands.put("GET:/online-shop/admin/users", new UsersAdministrationCommand());
        commands.put("GET:/online-shop/admin/users/add", new AddBlacklistCommand());
        commands.put("GET:/online-shop/admin/users/remove", new RemoveBlacklistCommand());
        commands.put("GET:/online-shop/admin/users/update", new UpdateUserCommand());
        commands.put("POST:/online-shop/admin/users/update", new UpdateUserSubmitCommand());
        commands.put("GET:/online-shop/admin/goods/update", new UpdateGoodsCommand());
        commands.put("POST:/online-shop/admin/goods/update", new UpdateGoodsSubmitCommand());
    }
}