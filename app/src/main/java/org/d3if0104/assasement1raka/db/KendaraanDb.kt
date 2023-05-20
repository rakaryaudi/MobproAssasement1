package org.d3if0104.assasement1raka.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KendaraanEntity::class], version = 1, exportSchema = false)
abstract class KendaraanDb : RoomDatabase() {

    abstract val  dao: KendaraanDao

    companion object{

        @Volatile
        private var INSTANCE: KendaraanDb? = null

        fun getInstance(context: Context): KendaraanDb{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KendaraanDb::class.java,
                        "kendaraan.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}