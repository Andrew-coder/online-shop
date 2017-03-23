package online.shop.controller.validators;

import online.shop.model.entity.Order;

/**
 * Created by andri on 1/25/2017.
 */
public class OrderValidator implements Validator<Order> {
    @Override
    public Errors validate(Order order) {
        return new Errors();
    }
}
