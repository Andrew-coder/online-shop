package online.shop.controller;

import online.shop.controller.commands.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static online.shop.utils.constants.PagesPaths.HOME_PAGE;

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
        Command command = commands.getOrDefault(key, (req , resp)->HOME_PAGE );
        String viewPage = command.execute(request, response);
        request.getRequestDispatcher(viewPage).forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        commands.put("GET:/online-shop/", new HomeCommand());
        commands.put("GET:/online-shop/login", new LoginCommand());
        commands.put("POST:/online-shop/login", new LoginSubmitCommand());
        commands.put("GET:/online-shop/basket", new ShowBasketCommand());
    }
}