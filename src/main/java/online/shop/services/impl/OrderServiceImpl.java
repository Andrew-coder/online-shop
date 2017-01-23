package online.shop.services.impl;

import online.shop.dao.DaoFactory;
import online.shop.services.OrderService;

/**
 * Created by andri on 1/23/2017.
 */
public class OrderServiceImpl implements OrderService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private OrderServiceImpl(){}

    private static OrderService instance;

    public static synchronized  OrderService getInstance(){
        if(instance==null){
            instance = new OrderServiceImpl();
        }
        return instance;
    }


}

