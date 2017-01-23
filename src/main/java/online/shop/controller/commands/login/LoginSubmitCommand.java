package online.shop.controller.commands.login;

import online.shop.controller.commands.Command;
import online.shop.model.entity.User;
import online.shop.services.UserService;
import online.shop.services.impl.UserServiceImpl;
import online.shop.utils.constants.PagesPaths;
import online.shop.utils.constants.Attributes;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;



/**
 * Created by andri on 1/21/2017.
 */
public class LoginSubmitCommand implements Command {
    public static final String PARAM_EMAIL = "login";
    public static final String PARAM_PASSWORD ="password";

    UserService userService = UserServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageToGo = PagesPaths.SLASH;
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        if( email != null && password != null ){
            Optional<User> user;
            user = userService.login(email, password);
            if( user.isPresent() ){
                User person = user.get();
                request.getSession().setAttribute(Attributes.USER, person);
                if(person.isWorker()) {
                    pageToGo = PagesPaths.ADMIN;
                }
            }
        }

        return pageToGo;
    }
}
