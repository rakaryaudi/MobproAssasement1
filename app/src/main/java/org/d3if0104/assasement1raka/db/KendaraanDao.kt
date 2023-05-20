package org.d3if0104.assasement1raka.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KendaraanDao {

    @Insert
    fun insert(kendaraan: KendaraanEntity)

    @Query("SELECT * FROM kendaraan ORDER BY id DESC")
    fun getLastKendaraan(): LiveData<List<KendaraanEntity>>

    @Query("DELETE FROM kendaraan")
    fun clearData()

}