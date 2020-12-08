package org.example.servlet;


import org.example.dao.loginDAO;
import org.example.exception.AppException;
import org.example.model.User;
import org.example.servlet.AbstractBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends AbstractBaseServlet {


    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        User user= loginDAO.query(username);
        if(user==null){
            throw new AppException("login002","用户不存在");
        }
        if(!user.getPassword().equals(password)){
            throw new AppException("LOGIN003","用户名或密码错误");
        }
        //登录成功  创建session
        HttpSession session=req.getSession();
        session.setAttribute("user",user);

        return null;

        //可以根据用户名进行查询，也可以根据名字+密码进行查询


//        if("abc".equals(username)){
//            return null;
//            //resp.sendRedirect("jsp/articleList.jsp");
//        }else{
//            throw new AppException("Login001错误码","用户名密码错误");
//        }

    }

}
