package org.example.servlet;


import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends AbstractBaseServlet{

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception {
        HttpSession session=req.getSession(false);
        User user=(User)session.getAttribute("user");


        //请求数据类型时application/json,需要使用输入流获取
        InputStream is=req.getInputStream();
        //json对象反序列化java对象
        Article a= JSONUtil.deserialize(is,Article.class);
        a.setUserId(user.getId());
        int num= ArticleDAO.insert(a);


        return null;
    }
}
