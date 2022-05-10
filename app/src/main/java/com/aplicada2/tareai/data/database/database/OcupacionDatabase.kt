package com.aplicada2.tareai.data.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aplicada2.tareai.data.database.dao.OcupacionDao
import com.aplicada2.tareai.data.database.entities.Ocupacion

@Database(entities = [Ocupacion::class], version = 1, exportSchema = false)

abstract class OcupacionDatabase: RoomDatabase() {

    abstract fun ocupacionDao(): OcupacionDao

    companion object{
        @Volatile
        private var INSTANCE: OcupacionDatabase? = null

        fun getDatabaseOcupacion(context: Context): OcupacionDatabase {
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OcupacionDatabase::class.java,
                    "tabla_ocupacion"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}