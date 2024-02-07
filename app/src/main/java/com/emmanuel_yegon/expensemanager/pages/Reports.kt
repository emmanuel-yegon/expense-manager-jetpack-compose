package com.emmanuel_yegon.expensemanager.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.emmanuel_yegon.expensemanager.R
import com.emmanuel_yegon.expensemanager.components.charts.MonthlyChart
import com.emmanuel_yegon.expensemanager.components.charts.WeeklyChart
import com.emmanuel_yegon.expensemanager.components.charts.YearlyChart
import com.emmanuel_yegon.expensemanager.components.expensesList.ExpensesList
import com.emmanuel_yegon.expensemanager.mock.mockExpenses
import com.emmanuel_yegon.expensemanager.models.Recurrence
import com.emmanuel_yegon.expensemanager.ui.theme.LabelSecondary
import com.emmanuel_yegon.expensemanager.ui.theme.TopAppBarBackground
import com.emmanuel_yegon.expensemanager.ui.theme.Typography
import com.emmanuel_yegon.expensemanager.view.models.ReportsViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reports(navController: NavController, vm: ReportsViewModel = viewModel()) {

    val state by vm.uiState.collectAsState()

    val recurrences = listOf(
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly,
    )

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Reports") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground
                ),
                actions = {
                    IconButton(onClick = vm::openRecurrenceMenu) {
                        Icon(painter = painterResource(id = R.drawable.ic_today), contentDescription = "Change Recurrence")
                    }

                    DropdownMenu(
                        expanded = state.recurrenceMenuOpened,
                        onDismissRequest = vm::closeRecurrenceMenu) {
                        recurrences.forEach { recurrence ->
                            DropdownMenuItem(
                                text = { Text(recurrence.name) },
                                onClick = {
                                    vm.setRecurrence(recurrence)
                                    vm.closeRecurrenceMenu()
                                })
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text("12 Feb - 18 Feb", style = Typography.titleSmall)
                        Row(modifier = Modifier.padding(top = 4.dp)) {
                            Text(
                                text = "USD",
                                style = Typography.bodyMedium,
                                color = LabelSecondary,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(text = "85", style = Typography.headlineMedium)
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text("Avg/day", style = Typography.titleSmall)
                        Row(modifier = Modifier.padding(top = 4.dp)) {
                            Text(
                                text = "USD",
                                style = Typography.bodyMedium,
                                color = LabelSecondary,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(text = "85", style = Typography.headlineMedium)
                        }
                    }

                }

                Box(
                    modifier = Modifier
                        .height(180.dp)
                        .padding(vertical = 16.dp)
                ) {
                    when(state.recurrence){
                        Recurrence.Weekly -> WeeklyChart(expenses = mockExpenses)
                        Recurrence.Monthly -> MonthlyChart(expenses = mockExpenses, month = LocalDate.now())
                        Recurrence.Yearly -> YearlyChart(expenses = mockExpenses)
                        else -> Unit
                    }
                }


                ExpensesList(
                    expenses = mockExpenses, modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                )

            }


        }
    )
}