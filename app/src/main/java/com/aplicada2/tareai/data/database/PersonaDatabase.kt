package com.aplicada2.tareai.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aplicada2.tareai.data.database.dao.PersonaDao
import com.aplicada2.tareai.data.database.entities.Persona

@Database(entities = [Persona::class], version = 1, exportSchema = false)

abstract class PersonaDatabase: RoomDatabase() {

    abstract fun personaDao(): PersonaDao

    companion object{
        @Volatile
        private var INSTANCE: PersonaDatabase? = null

        fun getDatabase(context: Context): PersonaDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonaDatabase::class.java,
                    "tabla_persona"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}