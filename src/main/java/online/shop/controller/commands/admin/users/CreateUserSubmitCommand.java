package online.shop.controller.commands.admin.users;

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
 * Created by andri on 1/27/2017.
 */
public class CreateUserSubmitCommand extends CommandExecuter {
    private static final Logger logger = Logger.getLogger(CreateUserSubmitCommand.class);
    private UserService userService = UserServiceImpl.getInstance();
    private UserValidator userValidator;

    public CreateUserSubmitCommand() {
        userValidator = new UserValidator();
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveCreationDataToRequest(request);
        Errors errors = new Errors();
        User user = extractUser(request, errors);
        errors.addErrors(userValidator.validate(user).getErrors());
        if(errors.hasErrors()){
            processErrors(request, errors);
            request.getRequestDispatcher(PagesPaths.USER_CREATE_PAGE).forward(request, response);
            return PagesPaths.FORWARD;
        }
        userService.create(user);
        logger.info(String.format("User %s %s was successfully registered",user.getName(), user.getSurname()));
        clearCreationDataFromRequest(request);
        return PagesPaths.USERS_ADMINISTRATION;
    }

    private User extractUser(HttpServletRequest request, Errors results){
        User.Builder builder = new User.Builder()
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

    private void saveCreationDataToRequest(HttpServletRequest request){
        request.setAttribute(Attributes.PREVIOUS_USER_NAME, request.getParameter("name"));
        request.setAttribute(Attributes.PREVIOUS_USER_SURNAME, request.getParameter("surname"));
        request.setAttribute(Attributes.PREVIOUS_USER_EMAIL, request.getParameter("email"));
        request.setAttribute(Attributes.PREVIOUS_USER_DATE, request.getParameter("birthDate"));
        request.setAttribute(Attributes.PREVIOUS_USER_PASSWORD, request.getParameter("password"));
        request.setAttribute(Attributes.PREVIOUS_USER_ROLE, request.getParameter("role"));
    }

    private void clearCreationDataFromRequest(HttpServletRequest request){
        request.removeAttribute(Attributes.PREVIOUS_USER_NAME);
        request.removeAttribute(Attributes.PREVIOUS_USER_SURNAME);
        request.removeAttribute(Attributes.PREVIOUS_USER_EMAIL);
        request.removeAttribute(Attributes.PREVIOUS_USER_DATE);
        request.removeAttribute(Attributes.PREVIOUS_USER_PASSWORD);
        request.removeAttribute(Attributes.PREVIOUS_USER_ROLE);
    }

    private void processErrors(HttpServletRequest request, Errors errors){
        logger.error("Wrong input user data in creation");
        request.setAttribute(Attributes.REGISTRATION_ERRORS, errors);
    }
}
