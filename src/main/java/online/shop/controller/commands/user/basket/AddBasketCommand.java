package online.shop.controller.commands.user.basket;

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

import java.util.*;

/**
 * Created by andri on 1/22/2017.
 */
public class AddBasketCommand implements Command {
    private GoodsService goodsService = GoodsServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Goods,Integer> goodsItems = (Map<Goods, Integer>) request.getSession().getAttribute("goods");
        if(goodsItems==null){
            goodsItems = new HashMap<>();
        }
        int id = Integer.parseInt(request.getParameter(Attributes.GOODS_ID));
        Optional<Goods> goods = goodsService.findById(id);
        if(goods.isPresent()){
            Goods g = goods.get();
            if(!goodsItems.keySet().contains(g)) {
                goodsItems.put(g, 1);
            }
        }
        request.getSession().setAttribute(Attributes.GOODS, goodsItems);
        return "../basket";
    }
}
