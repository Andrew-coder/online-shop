package online.shop.controller.commands.user.register;

import online.shop.controller.commands.Command;
import online.shop.controller.validators.UserRegisterValidator;
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
import java.util.Optional;

import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.LoggerMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

/**
 * Created by andri on 1/22/2017.
 */
public class RegisterSubmitCommand implements Command {
    private static final Logger logger = Logger.getLogger(RegisterSubmitCommand.class);
    private UserService userService = UserServiceImpl.getInstance();
    private UserRegisterValidator userValidator;

    public RegisterSubmitCommand(){
        userValidator = new UserRegisterValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        saveRegisterDataToRequest(request);
        Errors errors = new Errors();
        User user = extractUser(request, errors);
        errors.addErrors(userValidator.validate(user).getErrors());
        if(isUserRegistered(user.getEmail())){
            errors.addError(Attributes.USER_EMAIL, ErrorMessages.EMAIL_ALREADY_EXISTS);
        }
        if(errors.hasErrors()){
            processErrors(request, errors);
            request.getRequestDispatcher(PagesPaths.REGISTER_PAGE).forward(request, response);
            return PagesPaths.FORWARD;
        }
        userService.create(user);
        logger.info(String.format("User %d was succesfully registered",user.getId()));
        request.getRequestDispatcher(PagesPaths.REGISTER_SUCCESFULL_PAGE).forward(request, response);
        clearRegisterDataFromRequest(request);
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
            logger.info(LoggerMessages.SUCCESFULL_USER_REGISTER_INFO_PARSE);
        }
        catch (ParseException exception){
            logger.error(LoggerMessages.ERROR_PARSING_INPT_DATE);
            results.addError(Attributes.USER_DATE, ErrorMessages.WRONG_USER_DATE);
        }

        return builder.build();
    }

    private boolean isUserRegistered(String email){
        Optional<User> user = userService.findUserByEmail(email);
        return user.isPresent();
    }

    private void processErrors(HttpServletRequest request, Errors errors){
        logger.error("Wrong input data in registration");
        request.setAttribute(Attributes.REGISTRATION_ERRORS, errors);
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
