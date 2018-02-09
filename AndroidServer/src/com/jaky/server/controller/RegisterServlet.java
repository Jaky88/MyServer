package com.jaky.server.controller;

import com.jaky.server.bean.RegisterFormBean;
import com.jaky.server.bean.User;
import com.jaky.server.exception.UserExistException;
import com.jaky.server.service.BusinessService;
import com.jaky.server.service.BusinessServiceImpl;
import com.jaky.server.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//处理用户注册请求
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RegisterFormBean formbean = WebUtils.request2Bean(request, RegisterFormBean.class);

        if(!formbean.validate()){
            request.setAttribute("formbean", formbean);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        //将formbean中的数据填充到User中
        User user = new User();
        try {
            //注册BeanUtils转换String到Date的转换器。
            ConvertUtils.register(new DateLocaleConverter(), Date.class);

            //将formbean的属性值拷贝到user相应的属性上。
            BeanUtils.copyProperties(user, formbean);
            user.setId(WebUtils.makeID());

            //注册用户
            BusinessService service = new BusinessServiceImpl();
            service.register(user);

            //注册成功
            request.setAttribute("message", "注册成功！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);

        } catch (UserExistException e) {

            formbean.getErrors().put("username", "此用户名已存在");
            request.setAttribute("formbean", formbean);
            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();  //后台记录异常，便于程序员观察。
            request.setAttribute("message", "对不起，注册失败！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
