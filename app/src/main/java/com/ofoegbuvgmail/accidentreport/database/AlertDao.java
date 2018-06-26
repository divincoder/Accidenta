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
public interface AlertDao {

    @Query("SELECT * FROM alert")
    LiveData<List<AlertEntry>> loadAllAlerts();

    @Insert
    void insertAlert(AlertEntry alertEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAlert(AlertEntry alertEntry);

    @Delete
    void deleteAlert(AlertEntry alertEntry);

    // COMPLETED (1) Wrap the return type with LiveData
    @Query("SELECT * FROM alert WHERE id = :id")
    LiveData<AlertEntry> loadAlertById(int id);
}
