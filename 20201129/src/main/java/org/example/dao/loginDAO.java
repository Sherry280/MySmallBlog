package org.example.dao;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.DBUtil;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;


public class loginDAO {
    public static User query(String username){

        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{

            c=DBUtil.getConnection();
            String sql="select id,username,password,nickname,sex,birthday,head from user where username=?";
            ps=c.prepareStatement(sql);//预编译
            //设置占位符
            ps.setString(1,username);
            rs=ps.executeQuery();
            User user=null;
            //查询user的结果集，最多只有一条
            while(rs.next()){
                user=new User();
                //设置user的值
                user.setId(rs.getInt("id"));
                user.setUsername(username);
                user.setPassword(rs.getString("password"));


                user.setNickname(rs.getString("nickname"));
                user.setSex(rs.getBoolean("sex"));
                user.setHead(rs.getString("head"));

                java.sql.Date birthday=rs.getDate("birthday");
                if(birthday!=null)
                    user.setBirthday( new Date(birthday.getTime()));
                //user.setBirthday(new Date(birthday.getTime()));


            }
            return user;

        }catch(Exception e){
            throw new AppException("login001","查询用户操作出错",e);
        }finally {
            DBUtil.close(c,ps,rs);
        }


    }
}
