package online.shop.controller.commands.user.basket;

import online.shop.controller.commands.Command;
import online.shop.model.entity.Goods;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by andri on 1/23/2017.
 */
public class UpdateBasketCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Goods, Integer> goodsItems = (Map<Goods,Integer>) request.getSession().getAttribute("goods");
        if(goodsItems!=null && !goodsItems.isEmpty()){
            goodsItems.entrySet()
                    .stream()
                    .forEach(g -> g.setValue(extractGoodsAmount(request, g.getKey().getId())));
        }
        request.getSession().setAttribute(Attributes.GOODS, goodsItems);
        return PagesPaths.BASKET;
    }

    private int extractGoodsAmount(HttpServletRequest request, int id){
        return Integer.parseInt(request.getParameter("amount"+String.valueOf(id)));
    }
}
