package com.aplicada2.tareai.data.database.Repository

import androidx.lifecycle.LiveData
import com.aplicada2.tareai.data.database.dao.OcupacionDao
import com.aplicada2.tareai.data.database.entities.Ocupacion

class OcupacionRepository(private  val ocupacionDao: OcupacionDao) {

    val readAllData: LiveData<List<Ocupacion>> = ocupacionDao.readAllDataOcupacion()

    suspend fun addOcupacion(ocupacion: Ocupacion){
        ocupacionDao.addOcupacion(ocupacion)
    }

    suspend fun updateOcupacion(ocupacion: Ocupacion){
        ocupacionDao.updateOcupacion(ocupacion)
    }

    suspend fun deleteOcupacion(ocupacion: Ocupacion){
        ocupacionDao.deleteOcupacion(ocupacion)
    }

    suspend fun deleteAllOcupacion(){
        ocupacionDao.deleteAllOcupacion()
    }

}