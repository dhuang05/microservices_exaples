package com.nusino.lab.microservices.config.common;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class AuditInterceptor extends HandlerInterceptorAdapter implements RequestInterceptor{
	private String GLOBAL_REQUEST_ID_ATTRIBUTE = "global_request_id";

    @Override
    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) throws Exception {
    	String globalRequestId = (String) request.getHeader(GLOBAL_REQUEST_ID_ATTRIBUTE);
        if(globalRequestId == null || globalRequestId.trim().isEmpty()) {
        	globalRequestId = UUID.randomUUID().toString().replaceAll("-", "");
        }
        RequestInfoKeeper.setGlobalRequestId(globalRequestId);
        System.out.println("get header = " + globalRequestId);
    	return true;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(GLOBAL_REQUEST_ID_ATTRIBUTE, RequestInfoKeeper.getGlobalRequestId());
    }
}
