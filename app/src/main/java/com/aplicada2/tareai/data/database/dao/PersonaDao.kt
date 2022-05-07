package com.aplicada2.tareai.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aplicada2.tareai.data.database.entities.Persona

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPersona(persona : Persona)

    @Update
    suspend fun updatePersona(persona: Persona)

    @Delete
    suspend fun deletePersona(persona: Persona)

    @Query("DELETE FROM tabla_persona")
    suspend fun deleteAllPersona()

    @Query("SELECT * FROM TABLA_PERSONA ORDER BY PersonaId ASC")
    fun readAllData(): LiveData<List<Persona>>

}