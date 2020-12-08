package org.example.filter;

import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

//配置用户统一管理的会话管理的过滤器：匹配所有路径的请求
//服务端资源：/login不用校验session，其他都要校验，如果不通过，返回401，响应体内容随便
//前端资源：/jsp/校验session，不通过重定向到登录页面，/js/，/static/，/view/全部不校验
@WebFilter("/*")
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 每次http请求匹配到过滤器路径时，都会执行过滤器的doFilter
     * 如果要往下执行，调用filterChain.doFilter(request，response)
     * 否则自己处理响应
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse)response;
        //获取当前服务的请求路径
        String servletPath=req.getServletPath();
        //不需要登陆就可以访问的
        if(servletPath.startsWith("/js/")||servletPath.startsWith("/static/")||
                servletPath.startsWith("/view/")||servletPath.equals("/login")){
            chain.doFilter(request,response);

        }else{
            //获取session对象，没有就返回null
            HttpSession session=req.getSession(false);
            //验证用户是否登录，如果没有登陆，还需要根据前端或后端做不腰痛的处理
            if(session==null||session.getAttribute("user")==null){
                //前端重定向到登录页面
                if(servletPath.startsWith("/jsp/")){
                    resp.sendRedirect(basePath(req)+"/view/login.html");

                }else{//后端返回401状态🐎🐎
                    resp.setStatus(401);
                    resp.setCharacterEncoding("utf-8");
                    resp.setContentType("application/json");

                    JSONResponse json=new JSONResponse();
                    json.setCode("LOGIN000");
                    json.setMessage("用户没有登录，拒绝访问");
                    PrintWriter pw=resp.getWriter();
                    pw.println(JSONUtil.serialize(json));
                    pw.flush();
                    pw.close();

                }
            }else{
                //敏感资源，但已经登陆
                chain.doFilter(request,response);
            }
        }

    }

    /**
     * 根据http请求，动态的获取访问路径
     * @param req
     * @return
     */
    public static String basePath(HttpServletRequest req){
        String schema=req.getScheme();//http
        String host=req.getServerName();//主机ip或域名
        int port=req.getServerPort();//服务器端口号
        String contentPath=req.getContextPath();//应用上下文路径
        return schema+"://"+host+":"+port+contentPath;
    }

    @Override
    public void destroy() {

    }
}
