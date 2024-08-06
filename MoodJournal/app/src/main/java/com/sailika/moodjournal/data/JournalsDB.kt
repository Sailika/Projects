package com.sailika.moodjournal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sailika.moodjournal.model.Journal

@Database(
    entities = [Journal::class],
    version = 1
)
abstract class JournalsDB : RoomDatabase() {
    abstract val journalDao: JournalDao

    companion object{
        const val DB_NAME = "journals_db"
    }
}