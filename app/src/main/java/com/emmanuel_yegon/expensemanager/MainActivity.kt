package com.emmanuel_yegon.expensemanager

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emmanuel_yegon.expensemanager.pages.Expenses
import com.emmanuel_yegon.expensemanager.pages.Settings
import com.emmanuel_yegon.expensemanager.ui.theme.ExpenseManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseManagerTheme {

                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()

                // A surface container using the 'background' color from the theme
                Scaffold(
                    bottomBar = {
                        NavigationBar {

                            NavigationBarItem(
                                selected = backStackEntry.value?.destination?.route == "expenses",
                                onClick = { navController.navigate("expenses") },
                                icon = {
                                    androidx.compose.material3.Icon(
                                        painter = painterResource(id = R.drawable.upload),
                                        contentDescription = "Upload"
                                    )
                                },
                                label = {
                                    Text(text = "Expenses")
                                }
                            )

                            NavigationBarItem(
                                selected = backStackEntry.value?.destination?.route == "reports",
                                onClick = { navController.navigate("reports") },
                                icon = {
                                    androidx.compose.material3.Icon(
                                        painter = painterResource(id = R.drawable.bar_chart),
                                        contentDescription = "Reports"
                                    )
                                },
                                label = {
                                    Text(text = "Reports")
                                }
                            )

                            NavigationBarItem(
                                selected = backStackEntry.value?.destination?.route == "add",
                                onClick = { navController.navigate("add") },
                                icon = {
                                    androidx.compose.material3.Icon(
                                        painter = painterResource(id = R.drawable.add),
                                        contentDescription = "Add"
                                    )
                                },
                                label = {
                                    Text(text = "Add")
                                }
                            )

                            NavigationBarItem(
                                selected = backStackEntry.value?.destination?.route == "settings",
                                onClick = { navController.navigate("settings") },
                                icon = {
                                    androidx.compose.material3.Icon(
                                        painter = painterResource(id = R.drawable.outline_settings),
                                        contentDescription = "Settings"
                                    )
                                },
                                label = {
                                    Text(text = "Settings")
                                }
                            )
                        }
                    },

                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = "expenses") {
                            composable("expenses") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Expenses(navController, "Expenses")
                                }
                            }
                            composable("reports") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Greeting(name = "Reports")
                                }
                            }
                            composable("add") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Greeting(name = "Add")
                                }
                            }

                            composable("settings") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Settings(navController)
                                }
                            }

                            composable("settings/categories") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Greeting(name = "Categories")
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    ExpenseManagerTheme {
        Surface {
            Greeting("Android")
        }
    }
}