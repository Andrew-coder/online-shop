package online.shop.controller.commands.admin.goods;

import online.shop.controller.commands.Command;
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
 * Created by andri on 1/23/2017.
 */
public class GoodsAdministrationCommand implements Command {
    private GoodsService goodsService = GoodsServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goods> goods = goodsService.findAll();
        request.setAttribute(Attributes.GOODS, goods);
        return PagesPaths.GOODS_ADMINISTRATION_PAGE;
    }
}
