package online.shop.controller.filters;

import online.shop.model.entity.RoleType;
import online.shop.model.entity.User;
import online.shop.utils.constants.Attributes;
import online.shop.utils.constants.PagesPaths;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by andri on 1/22/2017.
 */
public class AdminAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) request).getRequestURI();

        if (isRequestAuthorized(request)) {
            chain.doFilter(request, response);
        }
        else {
            ((HttpServletResponse) response).sendRedirect(PagesPaths.LOGIN);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isRequestAuthorized(ServletRequest request){
        HttpSession session = ((HttpServletRequest)request).getSession();
        User user = (User)session.getAttribute(Attributes.USER);
        if(user!=null&&user.isWorker()){
            return true;
        }
        return false;
    }
}
