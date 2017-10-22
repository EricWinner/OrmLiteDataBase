package com.example.edwardadmin.ormlitedatabase.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by EdwardAdmin on 2017/10/5.
 */
@DatabaseTable(tableName = "t_userinfo")
public class UserInfo {

    @DatabaseField(id = true)
    private int userId;

    @DatabaseField(columnName = "username")
    private String username;

    @DatabaseField(columnName = "nickname")
    private String nickname;

    @DatabaseField(columnName = "usersex")
    private String usersex;

    @DatabaseField(columnName = "phone")
    private String phone;

    @DatabaseField(columnName = "password")
    private String password;

    public String getUsername() {
        return username;
    }


    public String getNickname() {
        return nickname;
    }


    public String getUsersex() {
        return usersex;
    }


    public String getPhone() {
        return phone;
    }


    public String getPassword() {
        return password;
    }

    public UserInfo(String username, String nickname, String usersex, String phone, String password) {
        this.username = username;
        this.nickname = nickname;
        this.usersex = usersex;
        this.phone = phone;
        this.password = password;
    }

}
