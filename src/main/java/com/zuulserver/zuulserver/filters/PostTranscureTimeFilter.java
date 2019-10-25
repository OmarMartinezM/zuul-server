package com.zuulserver.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTranscureTimeFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PostTranscureTimeFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("Entering to post filter");

        Long initialTime = (Long) request.getAttribute("initialTime");
        Long finalTime = System.currentTimeMillis();
        Long transcurredTime = finalTime - initialTime;

        log.info(String.format("Transcurred timme in seconds",transcurredTime.doubleValue()/1000.00));

        return null;
    }
}
