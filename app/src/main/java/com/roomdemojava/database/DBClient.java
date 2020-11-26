package com.roomdemojava.database;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.roomdemojava.utils.Constants;

public class DBClient {
    @SuppressLint("StaticFieldLeak")
    private static DBClient mInstance;
    private final AppDatabase mAppDatabase;

    public DBClient(Context context) {
        mAppDatabase = Room.databaseBuilder(context, AppDatabase.class, Constants.IDBConstants.DB_NAME).build();
    }

    public static synchronized DBClient newInstance(Context context) {
        if(mInstance==null ){
            mInstance= new DBClient(context);
        }
        return mInstance;
    }
    
    public AppDatabase appDatabase() {
        return mAppDatabase;
    }
}