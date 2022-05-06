package com.aplicada2.tareai.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aplicada2.tareai.data.database.entities.Persona

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPersona(persona : Persona)

    @Query("SELECT * FROM TABLA_PERSONA ORDER BY PersonaId ASC")
    fun readAllData(): LiveData<List<Persona>>
}