package online.shop.controller.validators;

import online.shop.model.entity.BaseEntity;

/**
 * Created by andri on 1/23/2017.
 */
public interface Validator<T extends BaseEntity> {
    ValidatorResults validate(T t);
}
