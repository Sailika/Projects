package com.sailika.moodjournal.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sailika.moodjournal.model.Journal
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {

    @Query("SELECT * FROM journal")
    fun getJournals(): Flow<List<Journal>>

    @Query("SELECT * FROM journal WHERE id = :id")
    suspend fun getJournalByID(id: Int): Journal?

    //if we call INSERT function with an existing id, it will update the existing entry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournal(journal: Journal)

    @Delete
    suspend fun deleteJournal(journal: Journal)

}