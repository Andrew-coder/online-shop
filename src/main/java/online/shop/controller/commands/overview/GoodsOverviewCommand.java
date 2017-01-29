package online.shop.controller.commands.overview;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
import online.shop.dao.DaoFactory;
import online.shop.model.entity.Goods;
import online.shop.services.GoodsService;
import online.shop.services.impl.GoodsServiceImpl;
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
public class GoodsOverviewCommand extends CommandExecuter {
    private static final Logger logger = Logger.getLogger(GoodsOverviewCommand.class);
    private GoodsService goodsService = GoodsServiceImpl.getInstance();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter(Attributes.SUBCATEGORY_ID));
            List<Goods> goods = goodsService.findGoodsBySubcategoryId(id);
            request.setAttribute("goods", goods);
        }
        catch (NumberFormatException exception){
            logger.error(LoggerMessages.WRONG_SUBCATEGORY_ID);
        }
        return PagesPaths.GOODS_PAGE;
    }

}
