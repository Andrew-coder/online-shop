package online.shop.controller.commands.admin.users;

import online.shop.controller.commands.Command;
import online.shop.controller.validators.Errors;
import online.shop.controller.validators.UserValidator;
import online.shop.model.entity.RoleType;
import online.shop.model.entity.User;
import online.shop.services.UserService;
import online.shop.services.impl.UserServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.LoggerMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andri on 1/27/2017.
 */
public class CreateUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PagesPaths.USER_CREATE_PAGE;
    }
}
