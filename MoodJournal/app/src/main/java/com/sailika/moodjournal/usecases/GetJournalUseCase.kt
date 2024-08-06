package com.sailika.moodjournal.usecases

import com.sailika.moodjournal.model.InvalidJournalException
import com.sailika.moodjournal.model.Journal
import com.sailika.moodjournal.repository.JournalRepository
import com.sailika.moodjournal.utils.JOrder
import com.sailika.moodjournal.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JournalUseCase(
    private val repository: JournalRepository
) {
    suspend fun GetJournal(id: Int): Journal? {
        return repository.getJournalById(id)
    }

    @Throws(InvalidJournalException::class)
    suspend  fun AddJournalEntry(journal: Journal) {
        if (journal.title.isBlank()) {
            throw InvalidJournalException("Title of the Journal can't be Empty!")
        }
        if (journal.content.isBlank()) {
            throw InvalidJournalException("Content of the Journal Entry can't be Empty!")
        }

        //add only when above two conditions meet
        repository.addJournal(journal)
    }

    suspend  fun DeleteJournalEntry(journal: Journal) {
        repository.deleteJournal(journal)
    }

     fun GetIndividualJournal(
        order: JOrder = JOrder.Date(OrderType.Descending)
    ): Flow<List<Journal>> {
        return repository.getJournals().map { journal ->
            //whatever the list we get from the repository, we map that to our newList
            when (order.orderType) {
                is OrderType.Ascending -> {
                    when (order) {
                        is JOrder.Title -> journal.sortedBy { it.title.lowercase() }
                        is JOrder.Date -> journal.sortedBy { it.timestamp }
                        is JOrder.Color -> journal.sortedBy { it.color }

                    }
                }

                is OrderType.Descending -> {
                    when (order) {
                        is JOrder.Title -> journal.sortedByDescending { it.title.lowercase() }
                        is JOrder.Date -> journal.sortedByDescending { it.timestamp }
                        is JOrder.Color -> journal.sortedByDescending { it.color }

                    }
                }
            }
        }
    }
}