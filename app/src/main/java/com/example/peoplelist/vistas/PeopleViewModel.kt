package com.example.peoplelist.vistas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.peoplelist.data.Repositorio
import com.example.peoplelist.data.local.PersonaDataBase
import com.example.peoplelist.data.remote.PeopleRetroFit
import kotlinx.coroutines.launch

class PeopleViewModel(application: Application): AndroidViewModel(application) {

    private val repositorio: Repositorio

    init {
        val api = PeopleRetroFit.gesRetroFitPeople()
        val personaDataBase = PersonaDataBase.getDatabase(application).getPersonaDao()
        repositorio = Repositorio(api,personaDataBase)
    }

    fun peopleLiveData() = repositorio.obtenerPersonaEntity()

    fun getAllPeople() = viewModelScope.launch {
        repositorio.cargarPeople()
    }

}