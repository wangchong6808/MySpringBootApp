package com.daimler.otr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class RequestInputStreamFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestInputStreamFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request = new CustomHttpServletRequestWrapper((HttpServletRequest)request);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private byte[] body;

        public CustomHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            /*try {
                body = IOUtils.toByteArray(request.getInputStream());
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
                body = new byte[0];
            }*/
        }
/*
        @Override
        public ServletInputStream getInputStream() throws IOException {
            return new ServletInputStream() {
                ByteArrayInputStream cachedBody = new ByteArrayInputStream(body);

                @Override
                public int read() throws IOException {
                    return cachedBody.read();
                }

                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener listener) {
                }
            };
        }*/

    }
}
