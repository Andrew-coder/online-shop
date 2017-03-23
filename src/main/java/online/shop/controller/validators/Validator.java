package online.shop.controller.validators;

/**
 * Created by andri on 1/23/2017.
 */
public interface Validator<T> {
    Errors validate(T t);
}
