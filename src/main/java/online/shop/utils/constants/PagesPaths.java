package online.shop.utils.constants;

/**
 * Created by andri on 1/20/2017.
 */
public final class PagesPaths {
    public static final String REDIRECT = "REDIRECT";
    public static final String FORWARD = "FORWARD";

    public static final String VIEW_JSP_CLASSPATH = "/WEB-INF/pages/";
    public static final String SLASH = "/";
    public static final String ROOT = "./";
    public static final String PARENT_ROOT = "../";
    public static final String HOME_PATH = "/online-shop/";
    public static final String ADMIN = "/admin";
    public static final String LOGIN = "/login";
    public static final String BASKET = "/basket";
    public static final String USERS_ADMINISTRATION = ADMIN+"/users";

    public static final String LOGIN_PAGE = VIEW_JSP_CLASSPATH + "login.jsp";
    public static final String REGISTER_PAGE = VIEW_JSP_CLASSPATH + "register.jsp";
    public static final String REGISTER_SUCCESFULL_PAGE = VIEW_JSP_CLASSPATH + "registerComplete.jsp";
    public static final String HOME_PAGE = VIEW_JSP_CLASSPATH +"index.jsp";
    public static final String BASKET_PAGE = VIEW_JSP_CLASSPATH + "basket.jsp";
    public static final String SUBCATEGORY_PAGE = VIEW_JSP_CLASSPATH + "subcategoryPage.jsp";
    public static final String GOODS_PAGE = VIEW_JSP_CLASSPATH + "goodsView.jsp";
    public static final String ADMIN_PAGE = VIEW_JSP_CLASSPATH + "dashboard.jsp";
    public static final String PURCHASE_PAGE = VIEW_JSP_CLASSPATH + "purchase.jsp";
    public static final String GOODS_ADMINISTRATION_PAGE = VIEW_JSP_CLASSPATH + "goodsAdministration.jsp";
    public static final String USERS_ADMINISTRATION_PAGE = VIEW_JSP_CLASSPATH + "usersAdministration.jsp";
}
