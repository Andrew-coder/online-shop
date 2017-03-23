package online.shop.controller;

import online.shop.controller.commands.Command;
import online.shop.controller.commands.HomeCommand;
import online.shop.controller.commands.LogOutCommand;
import online.shop.controller.commands.PageNotFoundCommand;
import online.shop.controller.commands.admin.AdminHomeCommand;
import online.shop.controller.commands.admin.blacklist.AddBlacklistCommand;
import online.shop.controller.commands.admin.blacklist.RemoveBlacklistCommand;
import online.shop.controller.commands.admin.goods.*;
import online.shop.controller.commands.admin.orders.OrdersAdministrationCommand;
import online.shop.controller.commands.admin.orders.UpdateOrderCommand;
import online.shop.controller.commands.admin.orders.UpdateOrderSubmitCommand;
import online.shop.controller.commands.admin.users.*;
import online.shop.controller.commands.login.LoginCommand;
import online.shop.controller.commands.login.LoginSubmitCommand;
import online.shop.controller.commands.overview.GoodsOverviewCommand;
import online.shop.controller.commands.overview.SubcategoryOverviewCommand;
import online.shop.controller.commands.user.basket.AddBasketCommand;
import online.shop.controller.commands.user.basket.RemoveBasketCommand;
import online.shop.controller.commands.user.basket.ShowBasketCommand;
import online.shop.controller.commands.user.basket.UpdateBasketCommand;
import online.shop.controller.commands.user.purchase.PurchaseCommand;
import online.shop.controller.commands.user.purchase.PurchaseSubmitCommand;
import online.shop.controller.commands.user.register.RegisterCommand;
import online.shop.controller.commands.user.register.RegisterSubmitCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andri on 1/27/2017.
 */
public class CommandHolder {
    private Map<String, Command> commands = new HashMap<>();

    public CommandHolder() {
        fillCommands();
    }

    Command findCommand(String key){
        return commands.getOrDefault(key, new PageNotFoundCommand());
    }

    private void fillCommands(){
        commands.put("GET:/online-shop/", new HomeCommand());
        commands.put("GET:/online-shop/login", new LoginCommand());
        commands.put("POST:/online-shop/login", new LoginSubmitCommand());
        commands.put("GET:/online-shop/logout", new LogOutCommand());
        commands.put("GET:/online-shop/basket", new ShowBasketCommand());
        commands.put("GET:/online-shop/basket/add", new AddBasketCommand());
        commands.put("GET:/online-shop/basket/remove", new RemoveBasketCommand());
        commands.put("POST:/online-shop/update", new UpdateBasketCommand());
        commands.put("GET:/online-shop/register", new RegisterCommand());
        commands.put("POST:/online-shop/register", new RegisterSubmitCommand());
        commands.put("GET:/online-shop/subcategory", new SubcategoryOverviewCommand());
        commands.put("GET:/online-shop/goods", new GoodsOverviewCommand());
        commands.put("GET:/online-shop/purchase", new PurchaseCommand());
        commands.put("POST:/online-shop/purchase", new PurchaseSubmitCommand());
        commands.put("GET:/online-shop/admin", new AdminHomeCommand());
        commands.put("GET:/online-shop/admin/goods", new GoodsAdministrationCommand());
        commands.put("GET:/online-shop/admin/users", new UsersAdministrationCommand());
        commands.put("GET:/online-shop/admin/orders", new OrdersAdministrationCommand());
        commands.put("GET:/online-shop/admin/users/add", new AddBlacklistCommand());
        commands.put("GET:/online-shop/admin/users/remove", new RemoveBlacklistCommand());
        commands.put("GET:/online-shop/admin/users/update", new UpdateUserCommand());
        commands.put("POST:/online-shop/admin/users/update", new UpdateUserSubmitCommand());
        commands.put("GET:/online-shop/admin/goods/update", new UpdateGoodsCommand());
        commands.put("POST:/online-shop/admin/goods/update", new UpdateGoodsSubmitCommand());
        commands.put("GET:/online-shop/admin/orders/update", new UpdateOrderCommand());
        commands.put("POST:/online-shop/admin/orders/update", new UpdateOrderSubmitCommand() );
        commands.put("GET:/online-shop/admin/users/create", new CreateUserCommand());
        commands.put("POST:/online-shop/admin/users/create", new CreateUserSubmitCommand());
        commands.put("GET:/online-shop/admin/goods/create", new CreateGoodsCommand());
        commands.put("POST:/online-shop/admin/goods/create", new CreateGoodsSubmitCommand());
    }
}
