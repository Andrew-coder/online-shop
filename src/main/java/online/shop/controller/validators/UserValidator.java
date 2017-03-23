package online.shop.controller.validators;

import online.shop.model.entity.User;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.RegExp;

import java.util.regex.Pattern;

/**
 * Created by andri on 1/23/2017.
 */
public class UserValidator implements Validator<User> {
    @Override
    public Errors validate(User user) {
        Errors results = new Errors();
        if(!Pattern.matches(RegExp.REGEX_NAME, user.getName())){
            results.addError(Attributes.USER_NAME, ErrorMessages.WRONG_USER_NAME);
        }
        if(!Pattern.matches(RegExp.REGEX_NAME, user.getSurname())){
            results.addError(Attributes.USER_SURNAME, ErrorMessages.WRONG_USER_SURNAME);
        }
        if(!Pattern.matches(RegExp.REGEX_MAIL, user.getEmail())){
            results.addError(Attributes.USER_EMAIL, ErrorMessages.WRONG_USER_EMAIL);
        }
        if(!Pattern.matches(RegExp.REGEX_PASSWORD, user.getPassword())){
            results.addError(Attributes.USER_PASSWORD, ErrorMessages.WRONG_USER_PASSWORD);
        }
        return results;
    }
}
