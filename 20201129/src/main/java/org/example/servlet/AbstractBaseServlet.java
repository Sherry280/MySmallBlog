package org.example.servlet;

import org.example.exception.AppException;
import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//模板模式设计父类
public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求体的编码格式
        req.setCharacterEncoding("UTF-8");
        //设置响应体的编码格式
        resp.setCharacterEncoding("UTF-8");
        //设置响应体的数据格式（浏览器需要使用什么方式来执行）
        resp.setContentType("application/json");
        //resp.setContentType("text/html");
        //Session会话管理：除注册和登录接口，其他都需要登陆后才能访问
        //req.getServletPath();获取请求服务路径
        //TODO

        JSONResponse json=new JSONResponse();
        try{

            //调用子类可以重写的方法
            Object data=process(req,resp);
            //子类的process执行完后没有抛异常，表示业务执行成功
            json.setSuccess(true);
            json.setData(data);
        }catch(Exception e) {
            //统一异常处理
            //处理异常 JDBC的异常，JSON的异常处理，自定义异常返回错误消息
            e.printStackTrace();

            //json.setSuccess(false)不用进行设置
            String code = "UNKNOWN";
            String s = "服务器未知的错误";
            if (e instanceof AppException) {
                code = ((AppException) e).getCode();
                s = e.getMessage();

            }
            ///前后端统一数据封装
            json.setCode(code);
            json.setMessage(s);
        }
            PrintWriter pw=resp.getWriter();
            pw.println(JSONUtil.serialize(json));
            pw.flush();
            pw.close();







    }


    protected abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception;






}
