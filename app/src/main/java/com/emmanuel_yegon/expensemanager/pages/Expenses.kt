package com.emmanuel_yegon.expensemanager.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emmanuel_yegon.expensemanager.components.PickerTrigger
import com.emmanuel_yegon.expensemanager.components.expensesList.ExpensesList
import com.emmanuel_yegon.expensemanager.mock.mockExpenses
import com.emmanuel_yegon.expensemanager.models.Recurrence
import com.emmanuel_yegon.expensemanager.ui.theme.ExpenseManagerTheme
import com.emmanuel_yegon.expensemanager.ui.theme.LabelSecondary
import com.emmanuel_yegon.expensemanager.ui.theme.TopAppBarBackground
import com.emmanuel_yegon.expensemanager.ui.theme.Typography
import com.emmanuel_yegon.expensemanager.view.models.ExpensesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(navController: NavController, vm: ExpensesViewModel = viewModel()) {

    val recurrences = listOf(
        Recurrence.Daily,
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly,
    )

    val state by vm.uiState.collectAsState()

    var recurrenceMenuOpened by remember {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Expenses") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Total for:",
                        style = Typography.bodyMedium
                    )
                    PickerTrigger(
                        state.recurrence.target ?: Recurrence.None.target,
                        onClick = {
                            recurrenceMenuOpened = !recurrenceMenuOpened
                        },
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    DropdownMenu(
                        expanded = recurrenceMenuOpened,
                        onDismissRequest = { recurrenceMenuOpened = false }) {
                        recurrences.forEach { recurrence ->
                            DropdownMenuItem(
                                text = { Text(recurrence.target) },
                                onClick = {
                                    vm.setRecurrence(recurrence)
                                    recurrenceMenuOpened = false
                                })
                        }
                    }
                }

                Row(modifier = Modifier.padding(vertical = 32.dp)) {
                    Text(
                        text = "$",
                        style = Typography.bodyMedium,
                        color = LabelSecondary,
                        modifier = Modifier.padding(end = 4.dp, top = 4.dp)
                    )
                    Text("${state.sumTotal}", style = Typography.titleLarge)
                }
                ExpensesList(expenses = mockExpenses, modifier= Modifier
                    .weight(1f)
                    .verticalScroll(
                        rememberScrollState()
                    ))
            }

        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ExpensesPreview() {
    ExpenseManagerTheme {
        Expenses(navController = rememberNavController())
    }
}

