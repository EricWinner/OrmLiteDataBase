package com.example.edwardadmin.ormlitedatabase.task;

import android.content.Context;

import com.example.edwardadmin.ormlitedatabase.database.UserInfoOpenHelper;
import com.example.edwardadmin.ormlitedatabase.model.UserInfo;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EdwardAdmin on 2017/10/5.
 */

public class UserOperationTask implements IOperationTaskInterface {

    private UserInfoOpenHelper mUserInfoOpenHelper;
    private AndroidDatabaseConnection mConnection;
    private Context mContext;
    private List<UserInfo> mUserInfoLists;

    public UserOperationTask (Context context) {
        mContext = context;
        mUserInfoOpenHelper = new UserInfoOpenHelper(mContext);
        mConnection = new AndroidDatabaseConnection(mUserInfoOpenHelper.getWritableDatabase(), true);
    }

    @Override
    public boolean startInsertOperationTask(UserInfo userInfo) {
        Savepoint savePoint = null;
        try {
            savePoint = mConnection.setSavePoint("start");
            mConnection.setAutoCommit(false);

            Dao<UserInfo, Integer> userInfoDao = mUserInfoOpenHelper.getDao(UserInfo.class);
            UserInfo info = userInfoDao.queryForId(1);
            if (info != null) {
                userInfoDao.update(info);
            } else {
                userInfoDao.create(userInfo);
            }
            mConnection.commit(savePoint);
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                mConnection.rollback(savePoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public ArrayList<UserInfo> startQueryOperationTask() {
        mUserInfoLists = new ArrayList<>();
        Savepoint savePoint = null;
        try {
            savePoint = mConnection.setSavePoint("start");
            mConnection.setAutoCommit(false);

            Dao<UserInfo, Integer> userInfoDao = mUserInfoOpenHelper.getDao(UserInfo.class);
            mUserInfoLists = userInfoDao.queryForAll();
            mConnection.commit(savePoint);
            return (ArrayList<UserInfo>) mUserInfoLists;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                mConnection.rollback(savePoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }
}
