package org.example.servlet;

import org.example.util.MyActionEnter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 富文本编辑器上传图片
 * 1.修改idea中的tomcat中的应用上下文路径。
 * 2.修改webapp/static/ueditor.config.js
 * 3.实现后端接口（和第二步是一致的）
 */
@WebServlet("/ueditor")
public class UEditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //返回一个绝对路径
        URL url = UEditorServlet.class.getClassLoader().getResource("config.json");


        String path= URLDecoder.decode(url.getPath(),"UTF-8");
        MyActionEnter enter=new MyActionEnter(req,path);
        String exec=enter.exec();
        PrintWriter ps=resp.getWriter();
        ps.println(exec);
        ps.flush();
        ps.close();

    }
}
