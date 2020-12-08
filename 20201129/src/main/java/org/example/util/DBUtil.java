package org.example.util;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.exception.AppException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private static final String URL="jdbc:mysql://localhost:3306/servlet_blog?user=root&password=0318&useUnicode=true&characterEncoding=UTF-8&useSSL=false";

    private static final DataSource DS=new MysqlDataSource();
    //工具类提供的JDBC操作：
    //不足：1.static代码块时出现错误，NoClassDefFoundError表示类可以找到，但是类加载失败
    //2.双重校验锁的单例模式来创建DataSource
    //3.工具类设计不是最优的，数据库框架ORM，框架Mybatis，都是模板设计模式


    static {
        ((MysqlDataSource)DS).setUrl(URL);
    }

    public static Connection getConnection(){
        try {
            return DS.getConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new AppException("DB001","获取数据库连接错误",e);
        }



    }
    public static void close(Connection c,Statement s){
        close(c,s,null);
    }

    public static void close(Connection c, Statement s, ResultSet r){
        try {
            if(r!=null){
                r.close();
            }
            if(s!=null){
                s.close();
            }
            if(c!=null){
                c.close();
            }
        } catch (SQLException e) {
            throw new AppException("DB002","数据库连接错误",e);
        }


    }




}
