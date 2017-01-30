package online.shop.controller;

import online.shop.controller.commands.*;
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
public class FrontController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(FrontController.class);
    private static final long serialVersionUID = 1L;

    private CommandHolder commandHolder;

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
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();
        String key = method+":"+path;
        Command command = commandHolder.findCommand(key);
        return command.execute(request, response);
    }

    @Override
    public void init() throws ServletException {
        commandHolder = new CommandHolder();
    }

    public void setCommandHolder(CommandHolder commandHolder) {
        this.commandHolder = commandHolder;
    }
}