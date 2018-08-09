package ua.kpi.tef2.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/")
public class EncoderFilter implements Filter {
    private final String encoding = "UTF-8";
    private final String contentType = "text/html";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setContentType(contentType);
        servletResponse.setCharacterEncoding(encoding);
    }
}
