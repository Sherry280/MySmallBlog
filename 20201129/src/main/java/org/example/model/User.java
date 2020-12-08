package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class User {

    private Integer id;
    private Boolean sex;
    private String username;
    private String password;
    private String nickname;
    private Date birthday;
    private String head;
}
