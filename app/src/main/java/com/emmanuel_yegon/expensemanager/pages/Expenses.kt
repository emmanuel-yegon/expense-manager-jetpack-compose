package com.emmanuel_yegon.expensemanager.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.emmanuel_yegon.expensemanager.ui.theme.TopAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(navController: NavController,name: String,) {
    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text("Expenses") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground
            ))
        },
        content = {innerPadding ->
            Column( modifier = Modifier.padding(innerPadding)) {
                Text("Hello, Expenses!")
            }
        }
    )
}