package com.aplicada2.tareai.iu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aplicada2.tareai.data.database.Repository.OcupacionRepository
import com.aplicada2.tareai.data.database.database.OcupacionDatabase
import com.aplicada2.tareai.data.database.entities.Ocupacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OcupacionViewModel(application : Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Ocupacion>>
    private val repository: OcupacionRepository

    init{
        val ocupacionDao = OcupacionDatabase.getDatabaseOcupacion(application).ocupacionDao()
        repository = OcupacionRepository(ocupacionDao)
        readAllData = repository.readAllData
    }

    fun addOcupacion(ocupacion: Ocupacion){
        viewModelScope.launch (Dispatchers.IO){
            repository.addOcupacion(ocupacion)
        }
    }

    fun updatePersona(ocupacion: Ocupacion){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateOcupacion(ocupacion)
        }
    }

    fun deletePersona(ocupacion: Ocupacion){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOcupacion(ocupacion)
        }
    }

    fun deleteAllPersona(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllOcupacion()
        }
    }
}