package com.ofoegbuvgmail.accidentreport.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AccidentDao {

    @Query("SELECT * FROM accident ORDER BY timeOfAccident")
    LiveData<List<AccidentEntry>> loadAllAccidents();

    @Insert
    void insertTask(AccidentEntry accidentEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(AccidentEntry accidentEntry);

    @Delete
    void deleteTask(AccidentEntry taskEntry);

    // COMPLETED (1) Wrap the return type with LiveData
    @Query("SELECT * FROM accident WHERE id = :id")
    LiveData<AccidentEntry> loadAccidentById(int id);
}
