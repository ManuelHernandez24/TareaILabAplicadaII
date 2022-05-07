package com.aplicada2.tareai.iu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aplicada2.tareai.data.PersonaRepository
import com.aplicada2.tareai.data.database.PersonaDatabase
import com.aplicada2.tareai.data.database.entities.Persona
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonaViewModel(application : Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Persona>>
    private val repository: PersonaRepository

    init{
        val personaDao = PersonaDatabase.getDatabase(application).personaDao()
        repository = PersonaRepository(personaDao)
        readAllData = repository.readAllData
    }

    fun addPersona(persona: Persona){
        viewModelScope.launch (Dispatchers.IO){
            repository.addPersona(persona)
        }
    }
}