package com.example.edwardadmin.ormlitedatabase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.edwardadmin.ormlitedatabase.model.UserInfo;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by EdwardAdmin on 2017/10/5.
 */

public class UserInfoOpenHelper extends OrmLiteSqliteOpenHelper {

    public UserInfoOpenHelper(Context context) {
        super(context, "userinfo.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, UserInfo.class, true);
            TableUtils.createTable(connectionSource, UserInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
