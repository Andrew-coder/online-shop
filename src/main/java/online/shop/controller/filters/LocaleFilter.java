package online.shop.controller.filters;

import online.shop.controller.i18n.LocaleHolder;
import online.shop.utils.constants.Attributes;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by andri on 1/29/2017.
 */



public class LocaleFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LocaleFilter.class);
    private LocaleHolder localeHolder = new LocaleHolder(LocaleHolder.DEFAULT_LOCALE);
    private static final String MESSAGE_PATH = "i18n.messages";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        req.setCharacterEncoding(Attributes.UTF_8);
        HttpSession session = req.getSession();
        setResourceBundle(session);
        Locale locale = localeHolder.getCurrentLocale();
        String localeName = extractLocale(req);
        if(localeName != null) {
            locale = findSupportedLocale(localeName);
        }
        req.setAttribute(Attributes.LOCALE, locale);
        session.setAttribute(Attributes.LOCALE, locale);
        session.setAttribute("SUPPORTED_LOCALES",LocaleHolder.SUPPORTED_LOCALES);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private String extractLocale(HttpServletRequest request){
        return request.getParameter(Attributes.LANG);
    }

    private Locale findSupportedLocale(String localeName) {
        for (Locale locale : LocaleHolder.SUPPORTED_LOCALES) {
            if (locale.getLanguage().equals(localeName)) {
                localeHolder.setCurrentLocale(locale);
                return locale;
            }
        }
        return LocaleHolder.DEFAULT_LOCALE;
    }

    private void setResourceBundle(HttpSession session){
        if(session.getAttribute(Attributes.BUNDLE_FILE)==null){
            session.setAttribute(Attributes.BUNDLE_FILE, MESSAGE_PATH);
        }
    }
}
