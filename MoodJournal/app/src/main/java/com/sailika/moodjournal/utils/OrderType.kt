package com.sailika.moodjournal.utils

sealed class OrderType{
    object Ascending : OrderType()
    object Descending : OrderType()
}
