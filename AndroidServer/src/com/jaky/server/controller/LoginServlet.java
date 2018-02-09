package com.jaky.server.controller;

import com.jaky.server.bean.User;
import com.jaky.server.service.BusinessService;
import com.jaky.server.service.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//处理用户登陆请求
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        BusinessService service = new BusinessServiceImpl();
        User user = service.login(username, password);

        if(user == null){
            request.setAttribute("message", "对不起，用户名或密码错误！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

        request.getSession().setAttribute("user", user);
        request.setAttribute("message", "恭喜："+user.getUsername()+"，登陆成功！本页面将在5秒后跳到首页。");
        String contextPath = request.getContextPath();
        response.setHeader("refresh", "5;url="+contextPath);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
