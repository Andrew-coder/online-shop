package online.shop.controller.commands.admin.users;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
import online.shop.model.entity.RoleType;
import online.shop.model.entity.User;
import online.shop.services.UserService;
import online.shop.services.impl.UserServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by andri on 1/23/2017.
 */
public class UsersAdministrationCommand extends CommandExecuter {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.findAll();
        request.setAttribute(Attributes.USERS, users);
        for(User user:users){
            request.setAttribute(String.valueOf(user.getId()), userService.isUserInBlacklist(user.getId()));
        }
        return PagesPaths.USERS_ADMINISTRATION_PAGE;
    }
}
