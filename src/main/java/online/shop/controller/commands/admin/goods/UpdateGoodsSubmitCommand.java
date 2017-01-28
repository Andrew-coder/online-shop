package online.shop.controller.commands.admin.goods;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.CommandExecuter;
import online.shop.services.GoodsService;
import online.shop.services.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andri on 1/24/2017.
 */
public class UpdateGoodsSubmitCommand extends CommandExecuter {
    private GoodsService goodsService = GoodsServiceImpl.getInstance();
    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}

