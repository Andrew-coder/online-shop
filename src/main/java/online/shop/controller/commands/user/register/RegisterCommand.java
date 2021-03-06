package online.shop.controller.commands.user.register;

import online.shop.controller.commands.Command;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andri on 1/22/2017.
 */
public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PagesPaths.REGISTER_PAGE;
    }
}
