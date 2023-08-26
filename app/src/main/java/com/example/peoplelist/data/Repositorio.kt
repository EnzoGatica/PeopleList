package com.example.peoplelist.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.peoplelist.data.local.PersonaDao
import com.example.peoplelist.data.local.PersonaEntity
import com.example.peoplelist.data.remote.People
import com.example.peoplelist.data.remote.PeopleAPI

class Repositorio(private val peopleAPI: PeopleAPI, private val personaDao: PersonaDao) {

    fun obtenerPersonaEntity(): LiveData<List<PersonaEntity>> = personaDao.getAllPeople()

    suspend fun cargarPeople(){
        try {
            val respuesta = peopleAPI.getDataPeople()
            if (respuesta.isSuccessful) {
                val resp = respuesta.body()
                resp?.let { people ->
                    val personaEntity = people.map { it.transformarEntity() }
                    personaDao.inserAllPeople(personaEntity)
                }
            }else {
                Log.e("repositorio", respuesta.errorBody().toString())
            }
        } catch (exception: Exception) {
            Log.e("catch", "")
        }
    }

    private fun People.transformarEntity(): PersonaEntity = PersonaEntity(
        uuid = this.uuid,
        gender = this.gender,
        name = this.name,
        location = this.location,
        email = this.email,
        login = this.login,
        dob = this.dob,
        registered = this.registered,
        phone = this.phone,
        cell = this.cell,
        id = this.id,
        picture = this.picture,
        nat = this.nat
        )

}