package com.example.peoplelist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonaEntity::class], version = 1)
abstract class PersonaDataBase : RoomDatabase() {

    abstract fun getPersonaDao(): PersonaDao

    companion object {
        //@Volatile
        private var INSTANCE: PersonaDataBase? = null

        fun getDatabase(context: Context): PersonaDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonaDataBase::class.java,
                    "people_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}