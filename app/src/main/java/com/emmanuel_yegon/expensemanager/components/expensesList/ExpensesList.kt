package com.emmanuel_yegon.expensemanager.components.expensesList

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emmanuel_yegon.expensemanager.components.ExpensesDayGroup
import com.emmanuel_yegon.expensemanager.mock.mockExpenses
import com.emmanuel_yegon.expensemanager.models.Expense
import com.emmanuel_yegon.expensemanager.models.groupedByDay
import com.emmanuel_yegon.expensemanager.ui.theme.ExpenseManagerTheme


@Composable
fun ExpensesList(expenses:List<Expense>, modifier: Modifier = Modifier){

    val groupedExpenses = expenses.groupedByDay()

    Column(modifier=modifier) {
        if (groupedExpenses.isEmpty()) {
            Text(text = "No data for selected date range.", modifier=Modifier.padding(top=32.dp))
        } else {
            groupedExpenses.keys.forEach { date ->
                if (groupedExpenses[date] != null) {
                    ExpensesDayGroup(
                        date = date,
                        dayExpenses = groupedExpenses[date]!!,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Preview(){
    ExpenseManagerTheme {
        ExpensesList(mockExpenses)
    }
}