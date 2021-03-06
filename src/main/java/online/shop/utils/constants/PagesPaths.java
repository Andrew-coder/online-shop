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
    public static final String GOODS_ADMINISTRATION = ADMIN+"/goods";
    public static final String ORDER_ADMINISTRATION = ADMIN+"/orders";
    public static final String PURCHASE = "/purchase";

    public static final String LOGIN_PAGE = VIEW_JSP_CLASSPATH + "login.jsp";
    public static final String REGISTER_PAGE = VIEW_JSP_CLASSPATH + "/user/register.jsp";
    public static final String REGISTER_SUCCESFULL_PAGE = VIEW_JSP_CLASSPATH + "user/registerComplete.jsp";
    public static final String HOME_PAGE = VIEW_JSP_CLASSPATH +"index.jsp";
    public static final String BASKET_PAGE = VIEW_JSP_CLASSPATH + "user/basket.jsp";
    public static final String SUBCATEGORY_PAGE = VIEW_JSP_CLASSPATH + "user/subcategoryPage.jsp";
    public static final String PURCHASE_PAGE = VIEW_JSP_CLASSPATH + "user/purchase.jsp";
    public static final String GOODS_PAGE = VIEW_JSP_CLASSPATH + "user/goodsView.jsp";
    public static final String ADMIN_PAGE = VIEW_JSP_CLASSPATH + "admin/dashboard.jsp";
    public static final String GOODS_ADMINISTRATION_PAGE = VIEW_JSP_CLASSPATH + "admin/goodsAdministration.jsp";
    public static final String USERS_ADMINISTRATION_PAGE = VIEW_JSP_CLASSPATH + "admin/usersAdministration.jsp";
    public static final String ORDER_ADMINISTRATION_PAGE = VIEW_JSP_CLASSPATH + "admin/orderAdministration.jsp";
    public static final String ACCESS_DENIED_PAGE = VIEW_JSP_CLASSPATH + "accessDenied.jsp";
    public static final String GOODS_UPDATE_PAGE = VIEW_JSP_CLASSPATH + "admin/goodsUpdate.jsp";
    public static final String USERS_UPDATE_PAGE = VIEW_JSP_CLASSPATH + "admin/userUpdate.jsp";
    public static final String ORDERS_UPDATE_PAGE = VIEW_JSP_CLASSPATH + "admin/orderUpdate.jsp";
    public static final String ERROR_PAGE = VIEW_JSP_CLASSPATH + "error.jsp";
    public static final String PAGE_NOT_FOUND = VIEW_JSP_CLASSPATH + "pageNotFound.jsp";
    public static final String PURCHASE_SUCCESFULL_PAGE = VIEW_JSP_CLASSPATH + "user/successfulPurchasePage.jsp";
    public static final String USER_CREATE_PAGE = VIEW_JSP_CLASSPATH + "admin/userCreate.jsp";
    public static final String GOODS_CREATE_PAGE = VIEW_JSP_CLASSPATH + "admin/goodsCreate.jsp";
}
