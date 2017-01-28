package online.shop.utils.constants;

/**
 * Created by andri on 1/23/2017.
 */
public final class ErrorMessages {
    public static final String EMAIL_ALREADY_EXISTS = "The user with such email is already registered";
    public static final String WRONG_USER_NAME = "Wrong user name";
    public static final String WRONG_USER_SURNAME = "Wrong user surname";
    public static final String WRONG_USER_PASSWORD = "Wrong user password (at least 4 characters)";
    public static final String WRONG_USER_EMAIL = "Wrong user email";
    public static final String BLACK_LIST_USER = "Currently, you are in the blacklist of this website. For additional info contact with admin";
    public static final String WRONG_USER_DATE = "Wrong user date! Date format (yyyy-mm-dd)";
    public static final String WRONG_ORDER_DATA = "Wrong order data...";
    public static final String WRONG_ORDER_ID = "Wrong order id";

    public static final String UNEXISTING_GOODS = "Goods doesn't found";
    public static final String UNEXISTING_USER = "User doesn't found";
    public static final String SUBCATEGORIES_NOT_FOUND = "Not found amy subcategories";

    public static final String WRONG_BASKET_VALUES = "You entered wrong values!";

    /* DAO messages */
    public static final String ERROR_FIND_ONE_CATEGORY = "";
    public static final String ERROR_FIND_ALL_CATEGORIES = "";

    public static final String ERROR_FIND_ONE_SUBCATEGORY = "";
    public static final String ERROR_FIND_ALL_SUBCATEGORIES = "";

    public static final String ERROR_FIND_ONE_ORDER = "dao exception occured when retrieving order by id";
    public static final String ERROR_FIND_GOODS_ITEMS = "dao exception occured when retrieving goods items in order by order id";
    public static final String ERROR_FIND_ALL_ORDERS = "dao exception occured when retrieving all orders";
    public static final String ERROR_CREATE_ORDER = "Error occured when creating new order!";
    public static final String ERROR_CREATE_ORDER_ITEM = "Error occured when creating new order Item!";
    public static final String ERROR_UPDATE_ORDER = "Error occured when updating order!";

    public static final String ERROR_FIND_ONE_USER = "";
    public static final String ERROR_FIND_ALL_USERS = "";
    public static final String ERROR_CREATE_USERR = "";
    public static final String ERROR_UPDATE_USERR = "";

    public static final String ERROR_FIND_ONE_GOODS = "";
    public static final String ERROR_FIND_ALL_GOODS = "";
    public static final String ERROR_CREATE_GOODS = "";
    public static final String ERROR_UPDATE_GOODS = "";

    public static final String EMPTY_BASKET = "Your basket is currently empty";
    public static final String EMPTY_OBJECT = "Error! Object is empty...";
    public static final String UNSAVED_OBJECT = "Error! Object is unsaved...";

    public static final String GOODS_NOT_FOUND = "Goods wasn't found";
    public static final String ORDER_NOT_FOUND = "Order wasn't found ";

    public static final String UNKNOWN_ERROR_OCCURED = "Unknown error occured...";
}
