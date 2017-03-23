package online.shop.controller.commands.overview;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
import online.shop.dao.SubcategoryDao;
import online.shop.dao.jdbc.SubcategoryDaoImpl;
import online.shop.model.entity.Subcategory;
import online.shop.services.SubcategoryService;
import online.shop.services.impl.SubcategoryServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.LoggerMessages;
import online.shop.utils.constants.PagesPaths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by andri on 1/22/2017.
 */
public class SubcategoryOverviewCommand extends CommandExecuter {
    private static final Logger logger = Logger.getLogger(SubcategoryOverviewCommand.class);
    private SubcategoryService subcategoryService = SubcategoryServiceImpl.getInstance();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter(Attributes.CATEGORY_ID));
            List<Subcategory> subcategories = subcategoryService.findSubcategoriesByCategoryId(id);
            request.setAttribute(Attributes.SUBCATEGORIES, subcategories);
        }
        catch (NumberFormatException exception){
            logger.error(LoggerMessages.WRONG_CATEGORY_ID);
        }
        return PagesPaths.SUBCATEGORY_PAGE;
    }
}
