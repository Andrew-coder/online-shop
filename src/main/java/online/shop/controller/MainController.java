package online.shop.controller;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.HomeCommand;
import online.shop.controller.commands.LoginCommand;
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
        Command command = commands.getOrDefault(key, (req , resp)->PagesPaths.HOME_PAGE );
        String viewPage = command.execute(request, response);
        request.getRequestDispatcher(viewPage).forward(request, response);
        //response.sendRedirect(viewPage);
    }

    @Override
    public void init() throws ServletException {
        commands.put("GET:/", new HomeCommand());
        commands.put("GET:/login", new LoginCommand());
    }
}
