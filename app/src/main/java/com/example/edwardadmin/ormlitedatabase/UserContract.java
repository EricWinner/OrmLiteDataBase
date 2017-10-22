package com.example.edwardadmin.ormlitedatabase;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by EdwardAdmin on 2017/10/5.
 */

public class UserContract implements BaseColumns{

    public static final String TABLE_NAME = "User";
    public static final String AUTHORITY = "com.ormlite.android.ormlitecontentprovider.authority";
    public static final String CONTENT_URI_PATH = "user";

    public static final String MIMETYPE_TYPE = "user";
    public static final String MIMETYPE_NAME = "com.ormlite.android.ormlitecontentprovider.provider";

    public static final int CONTENT_URI_PATTERN_MANY = 1;
    public static final int CONTENT_URI_PATTERN_ONE = 2;

    public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

    private UserContract() {

    }
}

