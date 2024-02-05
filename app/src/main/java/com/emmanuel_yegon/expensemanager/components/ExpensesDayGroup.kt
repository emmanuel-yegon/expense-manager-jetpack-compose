package com.emmanuel_yegon.expensemanager.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emmanuel_yegon.expensemanager.models.DayExpenses
import com.emmanuel_yegon.expensemanager.ui.theme.LabelSecondary
import com.emmanuel_yegon.expensemanager.ui.theme.Typography
import com.emmanuel_yegon.expensemanager.utils.formatDay
import java.text.DecimalFormat
import java.time.LocalDate

@Composable
fun ExpensesDayGroup(date: LocalDate, dayExpenses: DayExpenses, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

        Text(
            date.formatDay(),
            style = Typography.headlineMedium,
            color = LabelSecondary
        )
        Divider(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))

        dayExpenses.expenses.forEach { expense ->
            ExpenseRow(
                expense = expense,
                modifier = Modifier.padding(top = 12.dp)
            )
        }


        Divider(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Total:", style = Typography.bodyMedium, color = LabelSecondary)
            Text(
                DecimalFormat("USD 0.#").format(dayExpenses.total),
                style = Typography.headlineMedium,
                color = LabelSecondary
            )
        }


    }
}

