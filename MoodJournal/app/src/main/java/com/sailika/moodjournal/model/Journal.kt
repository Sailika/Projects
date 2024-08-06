package com.sailika.moodjournal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sailika.moodjournal.theme.BabyBlue
import com.sailika.moodjournal.theme.BlackColor
import com.sailika.moodjournal.theme.Yellow
import com.sailika.moodjournal.theme.Red
import com.sailika.moodjournal.theme.Green

@Entity
data class Journal(
  val title : String,
  val content: String,
  val timestamp: Long,
  val color: Int,
  @PrimaryKey val id: Int? = null
){
    companion object{
      val moodColors = listOf(Red, Yellow, Green, BabyBlue, BlackColor)
    }
}

class InvalidJournalException(message:String): Exception(message)
