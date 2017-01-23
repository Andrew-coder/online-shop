package online.shop.controller.commands.login;

import online.shop.controller.commands.Command;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static online.shop.utils.constants.PagesPaths.LOGIN_PAGE;

/**
 * Created by andri on 1/21/2017.
 */
public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return LOGIN_PAGE;
    }
}
