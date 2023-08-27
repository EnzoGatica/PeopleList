package com.example.peoplelist.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonaDao {

    @Insert
    suspend fun insertPersona(personaEntity: PersonaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAllPeople(peopleList: PersonaEntity)

    @Query("Select * From persona_table")
    fun getAllPeople(): LiveData<List<PersonaEntity>>
}