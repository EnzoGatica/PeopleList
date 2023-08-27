package com.example.peoplelist.data.remote

import retrofit2.Response
import retrofit2.http.GET

//https://randomuser.me/api/?results=100

interface PeopleAPI {

    @GET("?results=100")
    suspend fun getDataPeople(): Response<People>

}