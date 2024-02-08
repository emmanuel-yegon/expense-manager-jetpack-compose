package com.emmanuel_yegon.expensemanager.pages

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
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.emmanuel_yegon.expensemanager.R
import com.emmanuel_yegon.expensemanager.components.ReportPage
import com.emmanuel_yegon.expensemanager.models.Recurrence
import com.emmanuel_yegon.expensemanager.ui.theme.TopAppBarBackground
import com.emmanuel_yegon.expensemanager.view.models.ReportsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Reports(navController: NavController, vm: ReportsViewModel = viewModel()) {

    val state = vm.uiState.collectAsState().value

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
            val numOfPages = when(state.recurrence){
                Recurrence.Weekly -> 53
                Recurrence.Monthly -> 12
                Recurrence.Yearly -> 1
                else -> 53
            }
            HorizontalPager(count = numOfPages, reverseLayout = true) { page ->
                ReportPage(innerPadding,page,state.recurrence)
            }
        }
    )
}