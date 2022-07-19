package com.company;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/start"})
public class NumberFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        if (httpRequest.getParameter(ConstantsJSP.NUMBER_NAME) == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath());
            return;
        }
        chain.doFilter(req, resp);
    }

}
