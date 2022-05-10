package com.aplicada2.tareai.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aplicada2.tareai.data.database.entities.Ocupacion

@Dao
interface OcupacionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOcupacion(ocupacion: Ocupacion)

    @Update
    suspend fun updateOcupacion(ocupacion: Ocupacion)

    @Delete
    suspend fun deleteOcupacion(ocupacion: Ocupacion)

    @Query("DELETE FROM tabla_ocupacion")
    suspend fun deleteAllOcupacion()

    @Query("SELECT * FROM TABLA_OCUPACION ORDER BY OcupacionId ASC")
    fun readAllDataOcupacion(): LiveData<List<Ocupacion>>

}