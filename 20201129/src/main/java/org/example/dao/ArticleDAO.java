package org.example.dao;

import org.example.exception.AppException;
import org.example.model.Article;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArticleDAO {

    public static List<Article> queryByUserId(Integer id) {
        List<Article> articles=new ArrayList<>();
        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            c=DBUtil.getConnection();
            //单表操作
            String sql="select id,title from article where user_id=?";
            ps=c.prepareStatement(sql);
            ps.setInt(1,id);


            rs=ps.executeQuery();
            while(rs.next()){
                Article a=new Article();
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));

                articles.add(a);

            }
            return articles;

        }catch (Exception e){
            throw new AppException("ART001","查询文章列表出错",e);
        }finally {
            DBUtil.close(c,ps,rs);
        }
    }

    public static int delete(String[] split) {
        Connection c=null;
        PreparedStatement ps=null;
        try{
            c=DBUtil.getConnection();
            StringBuilder sql=new StringBuilder("delete from Article where id in (");
            //1,2,3
            for(int i=0;i<split.length;i++){
                if(i!=0){
                    sql.append(",");
                }
                sql.append("?");
            }
            sql.append(")");
            //sql+="";
            //1,?,?
            ps=c.prepareStatement(sql.toString());

            //设置占位符
            for(int i=0;i<split.length;i++){
                ps.setInt(i+1,Integer.parseInt(split[i]));
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new AppException("ART004","文章删除出错",e);
        }finally {
            DBUtil.close(c,ps);
        }
    }

    public static int insert(Article a) {
        Connection c=null;
        PreparedStatement ps=null;
        try{
            c=DBUtil.getConnection();
           String sql="insert into article(title,content,user_id) values (?,?,?)";


            ps=c.prepareStatement(sql.toString());

            //设置占位符
            ps.setString(1,a.getTitle());
            ps.setString(2,a.getContent());
            ps.setInt(3,a.getUserId());
            //执行操作语句
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new AppException("ART005","文章添加出错",e);
        }finally {
            DBUtil.close(c,ps);
        }


    }

    public static Article query(int id) {
        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            c=DBUtil.getConnection();
            //id可有可无
            String sql="select id,title,content from article where id=?";


            ps=c.prepareStatement(sql);

            //设置占位符
            ps.setInt(1,id);


            //执行操作语句
            rs= ps.executeQuery();
            Article a=null;
            while(rs.next()){
                a=new Article();
                //根据结果集设置文章属性
                a.setId(id);
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));

            }
            return a;
        } catch (Exception e) {
            throw new AppException("ART006","查询文章详情出错",e);
        }finally {
            DBUtil.close(c,ps,rs);
        }

    }

    public static int update(Article a) {
        Connection c=null;
        PreparedStatement ps=null;

        try{
            c=DBUtil.getConnection();

            String sql="update article set title=?,content=? where id=?";

            ps=c.prepareStatement(sql);

            //设置占位符
            ps.setString(1,a.getTitle());
            ps.setString(2,a.getContent());
            ps.setInt(3,a.getId());


            //执行操作语句


           //只修改一条数据时
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new AppException("ART007","更新文章操作出错",e);
        }finally {
            DBUtil.close(c,ps);
        }


    }
}
