package online.shop.controller.filters;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MainFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("CtaticContentFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getRequestURI()
                .substring(((HttpServletRequest) request).getContextPath().length());

        if (path.startsWith("/css") || path.startsWith("/js") || path.startsWith("/images")) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/online-shop" + path).forward(request, response);
        }
    }

    @Override
    public void destroy() {}
}
