package online.shop.controller.commands;

import online.shop.controller.validators.Errors;
import online.shop.dao.exception.DaoException;
import online.shop.services.exception.ServiceException;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andri on 1/26/2017.
 */
public abstract class CommandExecuter implements Command {
    private static final Logger logger = Logger.getLogger(CommandExecuter.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            return performExecute(request, response);
        }
        catch (DaoException|ServiceException exception){
            logger.error(exception.getMessage());
            putErrorMessageInRequest(request, exception.getMessage());
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            putErrorMessageInRequest(request, ErrorMessages.UNKNOWN_ERROR_OCCURED);
        }
        request.getRequestDispatcher(PagesPaths.ERROR_PAGE).forward(request,response);
        return PagesPaths.FORWARD;
    }

    public void putErrorMessageInRequest(HttpServletRequest request, String message){
        request.setAttribute(Attributes.ERROR, message);
    }

    public abstract String performExecute(HttpServletRequest request, HttpServletResponse response)
                                                                        throws ServletException, IOException;
}
