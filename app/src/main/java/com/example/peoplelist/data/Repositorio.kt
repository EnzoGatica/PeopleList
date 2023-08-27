package com.example.peoplelist.data

import android.util.Log
import androidx.lifecycle.LiveData
/*
import com.example.peoplelist.data.local.Coordinates
import com.example.peoplelist.data.local.Dob
import com.example.peoplelist.data.local.Id
import com.example.peoplelist.data.local.Location
import com.example.peoplelist.data.local.Login
import com.example.peoplelist.data.local.Name
import com.example.peoplelist.data.local.Picture
import com.example.peoplelist.data.local.Registered
import com.example.peoplelist.data.local.Street
import com.example.peoplelist.data.local.Timezone

 */

import com.example.peoplelist.data.local.PersonaDao
import com.example.peoplelist.data.local.PersonaEntity

import com.example.peoplelist.data.remote.People
import com.example.peoplelist.data.remote.PeopleAPI
import com.example.peoplelist.data.remote.Results

class Repositorio(private val peopleAPI: PeopleAPI, private val personaDao: PersonaDao) {

    fun obtenerPersonaEntity(): LiveData<List<PersonaEntity>> = personaDao.getAllPeople()

    suspend fun cargarPeople(){
        try {
            val respuesta = peopleAPI.getDataPeople()
            if (respuesta.isSuccessful) {
                val resp = respuesta.body()
                resp?.results?.let {
                    it.forEach(){
                        personaDao.inserAllPeople(it.transformarEntity())
                    }

                }


            }else {
                Log.e("repositorio", respuesta.errorBody().toString())
            }
        } catch (exception: Exception) {
            Log.e("catch", exception.toString())
        }
    }

    private fun Results.transformarEntity(): PersonaEntity = PersonaEntity(
        this.login!!.uuid,
        this.gender,
        this.name?.title,
        this.picture?.large
    )

    /*
    private fun People.transforName(): Name = Name(
        this.name?.title,
        this.name?.first,
        this.name?.last
    )

    private fun People.transforLocation(): Location = Location(
        this.transforStreet(),
        this.location.city,
        this.location.state,
        this.location.country,
        this.location.postcode,
        this.transforCoordinates(),
        this.transforTimezone()
    )

    private fun People.transforStreet(): Street = Street(
        this.location.street.number,
        this.location.street.name
    )

    private fun People.transforCoordinates(): Coordinates = Coordinates(
        this.location.coordinates.latitude,
        this.location.coordinates.longitude
    )

    private fun People.transforTimezone(): Timezone = Timezone(
        this.location.timezone.offset,
        this.location.timezone.description
    )

    private fun People.transforLogin(): Login = Login(
        this.login.username,
        this.login.password,
        this.login.salt,
        this.login.md5,
        this.login.sha1,
        this.login.sha256
    )

    private fun People.transforDob(): Dob = Dob(
        this.dob.date,
        this.dob.age
    )

    private fun People.transforRegistered(): Registered = Registered(
        this.registered.date,
        this.registered.age
    )

    private fun People.transforId(): Id = Id(
        this.id.name,
        this.id.value
    )

    private fun People.transforPicture(): Picture = Picture(
        this.picture.large,
        this.picture.medium,
        this.picture.thumbnail
    )

     */

    fun String?.isNull(): String{
        val txt = this
        if (txt.isNullOrEmpty()) {return "No Disponible"}
        return txt
    }

}