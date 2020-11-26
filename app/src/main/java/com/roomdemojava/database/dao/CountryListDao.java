package com.roomdemojava.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.roomdemojava.database.entity.CountryListEntity;

import java.util.List;

@Dao
public interface CountryListDao {
    @Insert
    void insert(CountryListEntity entity);

    @Query("SELECT * FROM CountryListEntity")
    List<CountryListEntity> getAllDataFromRoom();

    @Query("delete from CountryListEntity")
    void delete();
}