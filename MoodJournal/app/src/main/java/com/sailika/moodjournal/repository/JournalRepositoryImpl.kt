package com.sailika.moodjournal.repository

import com.sailika.moodjournal.data.JournalDao
import com.sailika.moodjournal.model.Journal
import kotlinx.coroutines.flow.Flow

class JournalRepositoryImpl(
    private val dao: JournalDao
) : JournalRepository {
    override fun getJournals(): Flow<List<Journal>> {
        return dao.getJournals()
    }

    override suspend fun getJournalById(id: Int): Journal? {
        return dao.getJournalByID(id)
    }

    override suspend fun addJournal(journal: Journal) {
        dao.insertJournal(journal)
    }

    override suspend fun deleteJournal(journal: Journal) {
        dao.deleteJournal(journal)
    }
}
