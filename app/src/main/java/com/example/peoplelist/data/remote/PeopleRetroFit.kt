package com.example.peoplelist.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://randomuser.me/api/?results=100
class PeopleRetroFit {

    companion object{

        private const val BASE_URL = "https://randomuser.me/api/"

        fun gesRetroFitPeople(): PeopleAPI{

            val mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return mRetrofit.create(PeopleAPI::class.java)
        }
    }

}