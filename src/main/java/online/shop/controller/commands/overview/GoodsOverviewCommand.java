package online.shop.controller.commands.overview;

import online.shop.controller.commands.Command;
import online.shop.dao.DaoFactory;
import online.shop.model.entity.Goods;
import online.shop.services.GoodsService;
import online.shop.services.impl.GoodsServiceImpl;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by andri on 1/22/2017.
 */
public class GoodsOverviewCommand implements Command {
    GoodsService goodsService = GoodsServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(Attributes.SUBCATEGORY_ID));
        List<Goods> goods = goodsService.findGoodsBySubcategoryId(id);
        request.setAttribute("goods", goods);
        return PagesPaths.GOODS_PAGE;
    }
}
