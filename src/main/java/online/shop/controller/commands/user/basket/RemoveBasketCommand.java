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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by andri on 1/22/2017.
 */
public class RemoveBasketCommand implements Command {
    private GoodsService goodsService = GoodsServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goods> goodsItems = (List<Goods>) request.getSession().getAttribute("goods");
        int id = Integer.parseInt(request.getParameter(Attributes.GOODS_ID));
        if(goodsItems!=null && checkGoodsId(id)) {
            goodsItems = goodsItems
                    .stream()
                    .filter(goods -> goods.getId()!=id)
                    .collect(Collectors.toList());
        }
        request.getSession().setAttribute(Attributes.GOODS, goodsItems);
        return PagesPaths.ROOT;
    }

    private boolean checkGoodsId(int id){
        Optional<Goods> goods = goodsService.findById(id);
        if(goods.isPresent()){
            return true;
        }
        return false;
    }
}
