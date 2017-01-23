package online.shop.controller;

import online.shop.controller.commands.*;
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
    private static final long serialVersionUID = 1L;

    private Map<String , Command> commands = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();
        String key = method+":"+path;
        Command command = commands.getOrDefault(key, (req , resp)-> PagesPaths.HOME_PATH );
        String viewPage = command.execute(request, response);
        request.getRequestDispatcher(viewPage).forward(request, response);
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
        commands.put("GET:/online-shop/register", new RegisterCommand());
        commands.put("POST:/online-shop/register", new RegisterSubmitCommand());
        commands.put("GET:/online-shop/subcategory", new SubcategoryOverviewCommand());
        commands.put("GET:/online-shop/goods", new GoodsOverviewCommand());
    }
}