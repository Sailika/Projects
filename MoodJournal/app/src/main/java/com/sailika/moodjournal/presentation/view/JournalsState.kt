package com.sailika.moodjournal.presentation.view

import com.sailika.moodjournal.model.Journal
import com.sailika.moodjournal.utils.JOrder
import com.sailika.moodjournal.utils.OrderType

data class JournalsState(
    val journals: List<Journal> = emptyList(),
    val journalsOrder: JOrder = JOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
