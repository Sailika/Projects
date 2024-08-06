package com.sailika.moodjournal.di

import android.app.Application
import androidx.room.Room
import com.sailika.moodjournal.usecases.JournalUseCase
import com.sailika.moodjournal.data.JournalsDB
import com.sailika.moodjournal.repository.JournalRepositoryImpl
import com.sailika.moodjournal.repository.JournalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJournalDatabase(app: Application): JournalsDB {
        return Room.databaseBuilder(
            app,
            JournalsDB::class.java,
            JournalsDB.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideJournalRepository(db: JournalsDB): JournalRepository {
        return JournalRepositoryImpl(db.journalDao)
    }


    @Provides
    @Singleton
    fun provideJournalUseCases(repository: JournalRepository): JournalUseCase {
        return JournalUseCase(repository)
    }

}