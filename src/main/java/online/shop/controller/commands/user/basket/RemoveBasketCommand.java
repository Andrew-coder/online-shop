package online.shop.controller.commands.user.basket;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
import online.shop.model.dto.Basket;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by andri on 1/22/2017.
 */
public class RemoveBasketCommand extends CommandExecuter {
    private GoodsService goodsService = GoodsServiceImpl.getInstance();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Basket basket = (Basket) request.getSession().getAttribute(Attributes.BASKET);
        int id = Integer.parseInt(request.getParameter(Attributes.GOODS_ID));
        if(!basket.isEmpty() ) {
            Optional<Goods> goods = goodsService.findById(id);
            if(goods.isPresent()){
                basket.removeGoodsItem(goods.get());
            }
        }
        request.getSession().setAttribute(Attributes.BASKET, basket);
        response.sendRedirect(PagesPaths.BASKET);
        return PagesPaths.REDIRECT;
    }
}
