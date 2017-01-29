package online.shop.utils.constants;

/**
 * Created by andri on 1/23/2017.
 */
public final class RegExp {
    public static final String REGEX_NAME="[A-Z]{1}[a-z]{1,}";
    public static  final String REGEX_MAIL="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String REGEX_PASSWORD = "[A-Za-z0-9]{4,200}";
    public static final String REGEX_DATE = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
}
