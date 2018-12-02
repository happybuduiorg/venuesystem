package com.happybudui.venuesystem.filter;

import com.happybudui.venuesystem.wrapper.ResultGenerator;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@WebFilter(filterName = "userFilter",urlPatterns = {"/user/*"})
public class UserFilter implements Filter {
                                
    private String[] includeUrls = new String[]{"/user/login","/user/register"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        boolean needFilter = isNeedFilter(uri);

        if (!needFilter) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //需要过滤器
            // session中包含isLogin对象,则是登录状态
            if(session!=null&&session.getAttribute("isLogin") != null&&session.getAttribute("isLogin").equals("true")){
                filterChain.doFilter(request, response);
            }else{
                /*判断是否是ajax请求 这个暂时有问题
                String requestType = request.getHeader("X-Requested-With");
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(new JSONObject().fromObject(ResultGenerator.error("Should Login!")).toString());
                }else{
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    response.sendRedirect(request.getContextPath()+"/user/login.html");
                }
                */
                response.getWriter().write(JSONObject.fromObject(ResultGenerator.error("Should Login!")).toString());
            }
        }
    }

    private boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if(includeUrl.equals(uri)) {
                return false;
            }
        }

        return true;
    }
}
