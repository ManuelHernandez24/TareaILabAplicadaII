package com.aplicada2.tareai.data

import androidx.lifecycle.LiveData
import com.aplicada2.tareai.data.database.dao.PersonaDao
import com.aplicada2.tareai.data.database.entities.Persona

class PersonaRepository(private  val personaDao: PersonaDao) {

    val readAllData: LiveData<List<Persona>> = personaDao.readAllData()

    suspend fun addPersona(persona: Persona){
        personaDao.addPersona(persona)
    }

}