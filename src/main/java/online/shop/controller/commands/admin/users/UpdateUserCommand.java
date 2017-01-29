package online.shop.controller.commands.admin.users;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
import online.shop.controller.validators.Errors;
import online.shop.model.entity.User;
import online.shop.services.UserService;
import online.shop.services.impl.UserServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by andri on 1/24/2017.
 */
public class UpdateUserCommand extends CommandExecuter{
    private static final Logger logger = Logger.getLogger(UpdateUserCommand.class);
    private UserService userService = UserServiceImpl.getInstance();
    private static final String WRONG_USER_ID = "wrong user id parameter from request";

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<User> user = extractUser(request);
        Errors errors = new Errors();
        if(!user.isPresent()){
            errors.addError(Attributes.USER_ID, ErrorMessages.UNEXISTING_GOODS);
        }
        request.setAttribute(Attributes.USER, user.get());
        request.setAttribute(Attributes.ERRORS, errors);
        return PagesPaths.USERS_UPDATE_PAGE;
    }

    private Optional<User> extractUser(HttpServletRequest request){
        try {
            int id = Integer.parseInt(request.getParameter(Attributes.USER_ID));
            return userService.findById(id);
        }
        catch (NumberFormatException exception){
            logger.error(WRONG_USER_ID);
        }
        return Optional.empty();
    }
}
