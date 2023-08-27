package com.example.peoplelist.data.remote

import android.icu.text.IDNA
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class People(
    @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf(),
    @SerializedName("info"    ) var info    : IDNA.Info? = IDNA.Info(),
)

data class Results (
    @SerializedName("gender"     ) var gender     : String?     = null,
    @SerializedName("name"       ) var name       : Name?       = null,
    @SerializedName("location"   ) var location   : Location?   = null,
    @SerializedName("email"      ) var email      : String?     = null,
    @SerializedName("login"      ) var login      : Login?      = null,
    @SerializedName("dob"        ) var dob        : Dob?        = null,
    @SerializedName("registered" ) var registered : Registered? = null,
    @SerializedName("phone"      ) var phone      : String?     = null,
    @SerializedName("cell"       ) var cell       : String?     = null,
    @SerializedName("id"         ) var id         : Id?         = null,
    @SerializedName("picture"    ) var picture    : Picture?    = null,
    @SerializedName("nat"        ) var nat        : String?     = null

)

data class Name(
    val title: String?,
    val first: String?,
    val last: String?
)

data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: Coordinates,
    val timezone: Timezone
)

data class Street(
    val number: Int,
    val name: String
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

data class Timezone(
    val offset: String,
    val description: String
)

data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

data class Dob(
    val date: String,
    val age: Int
)

data class Registered(
    val date: String,
    val age: Int
)

data class Id(
    val name: String,
    val value: String?
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)