package com.happybudui.venuesystem.filter;

import com.happybudui.venuesystem.entity.UserEntity;
import com.happybudui.venuesystem.mapper.UserMapper;
import com.happybudui.venuesystem.wrapper.ResultGenerator;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@WebFilter(filterName = "adminFilter",urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    private final UserMapper userMapper;

    @Autowired
    AdminFilter(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if(session!=null&&session.getAttribute("isLogin") != null&&session.getAttribute("isLogin").equals("true")){
            UserEntity userEntity=userMapper.getUserInfoById((String)session.getAttribute("userId"));
            if(userEntity.getUserStatus()==2) {
                filterChain.doFilter(request, response);
            }else{
                response.getWriter().write(JSONObject.fromObject(ResultGenerator.error("Illegal Operation!")).toString());
            }
        }else {
                response.getWriter().write(JSONObject.fromObject(ResultGenerator.error("Should Login!")).toString());
        }
    }
}
