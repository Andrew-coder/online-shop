package online.shop.controller.commands.admin.blacklist;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
import online.shop.services.UserService;
import online.shop.services.impl.UserServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andri on 1/24/2017.
 */
public class RemoveBlacklistCommand extends CommandExecuter {
    private UserService userService = UserServiceImpl.getInstance();
    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter(Attributes.USER_ID));
        userService.removeUserFromBlackList(userId);
        response.sendRedirect(PagesPaths.USERS_ADMINISTRATION);
        return PagesPaths.REDIRECT;

    }
}
