package org.example.rickmortyapp.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.example.rickmortyapp.data.local.dao.UserPreferencesDao
import org.example.rickmortyapp.data.local.entity.CharacterOfTheDayEntity

const val DATABASE_NAME = "rick_morty_db.db"

expect object RickMortyCTor:RoomDatabaseConstructor<RickMortyDataBase>

@Database(entities = [CharacterOfTheDayEntity::class], version = 1)
@ConstructedBy(RickMortyCTor::class)
abstract class RickMortyDataBase:RoomDatabase() {
    abstract fun getPreferencesDao():UserPreferencesDao
}