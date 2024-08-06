package com.sailika.moodjournal.utils

sealed class JOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : JOrder(orderType)
    class Date(orderType: OrderType) : JOrder(orderType)
    class Color(orderType: OrderType) : JOrder(orderType)

    //This function would be accessible in UI
    fun copyJournalHelper(orderType: OrderType): JOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}

