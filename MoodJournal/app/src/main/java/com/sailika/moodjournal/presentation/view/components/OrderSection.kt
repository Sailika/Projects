package com.sailika.moodjournal.presentation.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sailika.moodjournal.utils.JOrder
import com.sailika.moodjournal.utils.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    jOrder: JOrder = JOrder.Date(OrderType.Descending),
    onOrderChange: (JOrder) -> Unit
) {
    Column(modifier = Modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                selected = jOrder is JOrder.Title,
                onSelected = { onOrderChange(JOrder.Title(jOrder.orderType)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Date",
                selected = jOrder is JOrder.Date,
                onSelected = { onOrderChange(JOrder.Date(jOrder.orderType)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Color",
                selected = jOrder is JOrder.Color,
                onSelected = { onOrderChange(JOrder.Color(jOrder.orderType)) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Making second Row
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = jOrder.orderType is OrderType.Descending,
                onSelected = {
                    onOrderChange(jOrder.copyJournalHelper(OrderType.Descending))
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Descending",
                selected = jOrder.orderType is OrderType.Ascending,
                onSelected = {
                    onOrderChange(jOrder.copyJournalHelper(OrderType.Ascending))
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OrderSectionPreview() {
    OrderSection(onOrderChange = {})
}