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

import java.util.*;

/**
 * Created by andri on 1/22/2017.
 */
public class AddBasketCommand extends CommandExecuter {
    private GoodsService goodsService = GoodsServiceImpl.getInstance();
    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Basket basket = (Basket) request.getSession().getAttribute(Attributes.BASKET);
        if(basket==null){
            basket= new Basket();
        }
        int id = Integer.parseInt(request.getParameter(Attributes.GOODS_ID));

        Optional<Goods> goods = goodsService.findById(id);
        if(goods.isPresent()){
            Goods g = goods.get();
            if(!basket.contains(g)) {
                basket.addGoodsItem(g, 1);
            }
        }
        request.getSession().setAttribute(Attributes.BASKET, basket);
        response.sendRedirect(PagesPaths.BASKET);
        return PagesPaths.REDIRECT;
    }
}
