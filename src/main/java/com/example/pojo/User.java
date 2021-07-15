package com.example.pojo;

import javax.persistence.*;

/**
 * @className:
 * @author: sir
 * @description: TODO
 * @date: 2021/6/26 - 22:59
 */
//此类和数据库表user产生关联关系，通过操作对象来操作表
//dao的意思：dao access object：数据访问对象，和数据操作相关的类放在此包中
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//这表示通过数据自增方式来产生ID
    @Column(name = "id")//此注释表示数据库字段id对应属性id
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "grants")
    private String grants;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrants() {
        return grants;
    }

    public void setGrants(String grants) {
        this.grants = grants;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", grants='" + grants + '\'' +
                '}';
    }
}

