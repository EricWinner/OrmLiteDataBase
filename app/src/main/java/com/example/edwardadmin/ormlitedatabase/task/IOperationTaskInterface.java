package com.example.edwardadmin.ormlitedatabase.task;

import com.example.edwardadmin.ormlitedatabase.model.UserInfo;

import java.util.ArrayList;

/**
 * Created by EdwardAdmin on 2017/10/5.
 */

public interface IOperationTaskInterface {

    boolean startInsertOperationTask(UserInfo userInfo);
    ArrayList<UserInfo> startQueryOperationTask();

}
