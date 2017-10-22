package com.example.edwardadmin.ormlitedatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.edwardadmin.ormlitedatabase.model.UserInfo;
import com.example.edwardadmin.ormlitedatabase.task.UserOperationTask;

import java.util.ArrayList;

import cn.carbs.android.library.MDDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mInsertUserInfoButton;
    private Button mQueryUserInfoButton;

    private EditText mUserNameText;
    private EditText mUserNickText;
    private EditText mUserPhoneText;
    private EditText mUserPasswordText;

    private RadioGroup mRadioSexGroup;
    private RadioButton mRadioManButton;
    private RadioButton mRadioWomanButton;

    private String mUserName;
    private String mUserNick;
    private String mUserPhone;
    private String mUserPassword;
    private String mUserSex;

    private MDDialog mDialog;

    private UserOperationTask mUserOperationTask;
    private ArrayList<UserInfo> mUserInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mInsertUserInfoButton = (Button) this.findViewById(R.id.insert_userinfo);
        mQueryUserInfoButton = (Button) this.findViewById(R.id.query_userinfo);

        mInsertUserInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCustomDialog();
            }
        });

        mQueryUserInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQueryTask();
            }
        });
    }

    private void createCustomDialog() {
        View view = View.inflate(this, R.layout.custom_dialog, null);
        initDialogViews(view);
        mDialog = new MDDialog.Builder(MainActivity.this)
                .setContentView(view)
                .setContentViewOperator(new MDDialog.ContentViewOperator() {
                    @Override
                    public void operate(View contentView) {

                    }
                })
                .setTitle("Insert UserInfo")
                .setNegativeButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startInsertTask();
                    }
                })
                .setPositiveButtonMultiListener(new MDDialog.OnMultiClickListener() {
                    @Override
                    public void onClick(View clickedView, View contentView) {

                    }
                })
                .setNegativeButtonMultiListener(new MDDialog.OnMultiClickListener() {
                    @Override
                    public void onClick(View clickedView, View contentView) {

                    }
                })
                .setOnItemClickListener(new MDDialog.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int index) {

                    }
                })
                .setWidthMaxDp(600)
                .setShowButtons(false)
                .create();
        mDialog.show();
    }

    private void startInsertTask() {
        Log.d(TAG, "startInsertTask!");
        mUserName = mUserNameText.getText().toString();
        mUserNick = mUserNickText.getText().toString();
        mUserPhone = mUserPhoneText.getText().toString();
        mUserPassword = mUserPasswordText.getText().toString();
        Log.d(TAG, "mUserName = " + mUserName + ",mUserNick = " + mUserNick + ",mUserPhone = " + mUserPhone + ",mUserPassword = " + mUserPassword + ",mUserSex = " + mUserSex);
        if (!TextUtils.isEmpty(mUserName) && !TextUtils.isEmpty(mUserNick) && !TextUtils.isEmpty(mUserPhone) && !TextUtils.isEmpty(mUserPassword) && !TextUtils.isEmpty(mUserSex)) {
            mUserOperationTask = new UserOperationTask(this);
            UserInfo info = new UserInfo(mUserName, mUserNick, mUserSex, mUserPhone, mUserPassword);
            mUserOperationTask.startInsertOperationTask(info);
        }
    }

    private void startQueryTask() {
        Log.d(TAG, "startQueryTask!");
        if (mUserOperationTask == null) {
            mUserOperationTask = new UserOperationTask(this);
        }
        mUserInfoList= mUserOperationTask.startQueryOperationTask();
        if (mUserInfoList.size() != 0) {
            for (UserInfo info : mUserInfoList) {
                if (info != null) {
                    Toast.makeText(this, "username = " + info.getUsername()
                            + ",nickname = " + info.getNickname()
                            + ",sex = " + info.getUsersex()
                            + ",phone = " + info.getPhone()
                            + ",password = " + info.getPassword(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
    }

    private void initDialogViews(View view) {
        mUserNameText = (EditText) view.findViewById(R.id.edit_name);
        mUserNickText = (EditText) this.findViewById(R.id.edit_nick);
        mUserPhoneText = (EditText) this.findViewById(R.id.edit_phone);
        mUserPasswordText = (EditText) this.findViewById(R.id.edit_password);

        mRadioSexGroup = (RadioGroup) this.findViewById(R.id.case_sex_group);
        mRadioManButton = (RadioButton) this.findViewById(R.id.case_sex_1);
        mRadioWomanButton = (RadioButton) this.findViewById(R.id.case_sex_2);

        mRadioSexGroup.setOnCheckedChangeListener(new RadioGroupListener());
    }

    class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == mRadioManButton.getId()) {
                mUserSex = getString(R.string.sex_man);
            } else if (checkedId == mRadioWomanButton.getId()) {
                mUserSex = getString(R.string.sex_woman);
            }
        }
    }
}
