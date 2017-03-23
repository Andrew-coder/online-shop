package online.shop.controller.commands.admin.users;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
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
 * Created by andri on 1/24/2017.
 */
public class UpdateUserSubmitCommand extends CommandExecuter {
    private static final Logger logger = Logger.getLogger(UpdateUserSubmitCommand.class);
    private UserService userService = UserServiceImpl.getInstance();
    private UserValidator userValidator;

    public UpdateUserSubmitCommand() {
        userValidator = new UserValidator();
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Errors errors = new Errors();
        User user = extractUser(request, errors);
        errors.addErrors(userValidator.validate(user).getErrors());
        if(errors.hasErrors()){
            processErrors(request, errors);
            request.setAttribute(Attributes.USER, user);
            request.getRequestDispatcher(PagesPaths.USERS_UPDATE_PAGE).forward(request, response);
            return PagesPaths.FORWARD;
        }
        userService.update(user);
        logger.info(String.format("User with %d id was successfully updated",user.getId()));
        return PagesPaths.USERS_ADMINISTRATION;
    }

    private User extractUser(HttpServletRequest request, Errors results){
        User.Builder builder = new User.Builder()
                .setId(Integer.parseInt(request.getParameter("id")))
                .setName(request.getParameter("name").toString())
                .setSurname(request.getParameter("surname").toString())
                .setEmail(request.getParameter("email").toString())
                .setPassword(request.getParameter("password").toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String roleName = request.getParameter("role");
        builder.setRole(RoleType.getRole(roleName));
        String birthDdate = request.getParameter("birthDate").toString();
        try {
            Date convertedDate = sdf.parse(birthDdate);
            builder.setBirthDate(convertedDate);
        }
        catch (ParseException exception){
            logger.error(LoggerMessages.ERROR_PARSING_INPUT_DATE);
            results.addError(Attributes.USER_DATE, ErrorMessages.WRONG_USER_DATE);
        }
        return builder.build();
    }


    private void processErrors(HttpServletRequest request, Errors errors){
        logger.error("Wrong input user data in creation");
        request.setAttribute(Attributes.ERRORS, errors);
    }
}
