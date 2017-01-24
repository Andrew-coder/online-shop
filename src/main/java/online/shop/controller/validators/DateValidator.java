package online.shop.controller.validators;

import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.ErrorMessages;
import online.shop.utils.constants.LoggerMessages;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by andri on 1/24/2017.
 */
public class DateValidator implements Validator<String>{
    private static final Logger logger = Logger.getLogger(DateValidator.class);


    @Override
    public Errors validate(String checkedString) {
        Errors results = new Errors();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(checkedString);
        }
        catch (ParseException exception){
            logger.error(LoggerMessages.ERROR_PARSING_INPT_DATE);
            results.addError(Attributes.USER_DATE, ErrorMessages.WRONG_USER_DATE);
        }
        return results;
    }
}
