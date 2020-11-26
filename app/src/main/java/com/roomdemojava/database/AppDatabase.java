package com.roomdemojava.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.roomdemojava.database.dao.CountryListDao;
import com.roomdemojava.database.entity.CountryListEntity;
import com.roomdemojava.utils.Constants;

@Database(entities = {CountryListEntity.class}, version = Constants.IDBConstants.COUNTRY_LIST_DB_VERSION)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryListDao CountryListDao();
}