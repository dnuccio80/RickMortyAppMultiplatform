package org.example.rickmortyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.example.rickmortyapp.data.local.entity.CharacterOfTheDayEntity

@Dao
interface UserPreferencesDao {

    @Query("SELECT * FROM CharacterOfTheDayEntity")
    suspend fun getCharacterOfTheDay(): CharacterOfTheDayEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacterOfTheDay(characterOfTheDayEntity: CharacterOfTheDayEntity)

}