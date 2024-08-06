package com.sailika.moodjournal.repository

import com.sailika.moodjournal.model.Journal
import kotlinx.coroutines.flow.Flow

//we declared an interface for repository, so as to make testing easier
interface JournalRepository {

    fun getJournals() : Flow<List<Journal>>

    suspend fun getJournalById(id:Int) : Journal?

    suspend fun addJournal(journal: Journal)

    suspend fun deleteJournal(journal: Journal
    )
}