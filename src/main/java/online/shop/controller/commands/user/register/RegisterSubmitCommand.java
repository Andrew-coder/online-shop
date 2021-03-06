package online.shop.controller.commands.user.register;

import online.shop.controller.commands.CommandExecuter;
import online.shop.controller.validators.UserValidator;
import online.shop.controller.validators.Errors;
import online.shop.model.entity.RoleType;
import online.shop.model.entity.User;
import online.shop.services.UserService;
import online.shop.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.LoggerMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

/**
 * Created by andri on 1/22/2017.
 */
public class RegisterSubmitCommand extends CommandExecuter {
    private static final Logger logger = Logger.getLogger(RegisterSubmitCommand.class);
    private UserService userService = UserServiceImpl.getInstance();
    private UserValidator userValidator;

    public RegisterSubmitCommand()
    {
        userValidator = new UserValidator();
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        saveRegisterDataToRequest(request);
        Errors errors = new Errors();
        User user = extractUser(request, errors);
        errors.addErrors(userValidator.validate(user).getErrors());
        if(errors.hasErrors()){
            processErrors(request, errors);
            request.getRequestDispatcher(PagesPaths.REGISTER_PAGE).forward(request, response);
            return PagesPaths.FORWARD;
        }
        userService.create(user);
        logger.info(String.format("User %s %s was successfully registered",user.getName(), user.getSurname()));
        clearRegisterDataFromRequest(request);
        request.getRequestDispatcher(PagesPaths.REGISTER_SUCCESFULL_PAGE).forward(request, response);
        return PagesPaths.FORWARD;
    }

    private User extractUser(HttpServletRequest request, Errors results){
        User.Builder builder = new User.Builder()
                .setName(request.getParameter("name").toString())
                .setSurname(request.getParameter("surname").toString())
                .setEmail(request.getParameter("email").toString())
                .setPassword(request.getParameter("password").toString())
                .setRole(RoleType.USER);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        logger.error("Wrong input data in registration");
        request.setAttribute(Attributes.ERRORS, errors);
    }

    private void saveRegisterDataToRequest(HttpServletRequest request){
        request.setAttribute(Attributes.PREVIOUS_USER_NAME, request.getParameter("name"));
        request.setAttribute(Attributes.PREVIOUS_USER_SURNAME, request.getParameter("surname"));
        request.setAttribute(Attributes.PREVIOUS_USER_EMAIL, request.getParameter("email"));
        request.setAttribute(Attributes.PREVIOUS_USER_DATE, request.getParameter("birthDate"));
        request.setAttribute(Attributes.PREVIOUS_USER_PASSWORD, request.getParameter("password"));
    }

    private void clearRegisterDataFromRequest(HttpServletRequest request){
        request.removeAttribute(Attributes.PREVIOUS_USER_NAME);
        request.removeAttribute(Attributes.PREVIOUS_USER_SURNAME);
        request.removeAttribute(Attributes.PREVIOUS_USER_EMAIL);
        request.removeAttribute(Attributes.PREVIOUS_USER_DATE);
        request.removeAttribute(Attributes.PREVIOUS_USER_PASSWORD);
    }
}
