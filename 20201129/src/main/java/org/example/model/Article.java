package org.example.model;

import com.mysql.fabric.xmlrpc.base.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Article {

    private Integer id;
    private String title;
    private String content;
    private Integer viewCount;
    private Data createTime;
    private Integer userId;


}
