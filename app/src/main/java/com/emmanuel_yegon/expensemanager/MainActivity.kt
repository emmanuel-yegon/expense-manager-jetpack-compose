package com.emmanuel_yegon.expensemanager

import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emmanuel_yegon.expensemanager.pages.Add
import com.emmanuel_yegon.expensemanager.pages.Categories
import com.emmanuel_yegon.expensemanager.pages.Expenses
import com.emmanuel_yegon.expensemanager.pages.Reports
import com.emmanuel_yegon.expensemanager.pages.Settings
import com.emmanuel_yegon.expensemanager.ui.theme.ExpenseManagerTheme
import com.emmanuel_yegon.expensemanager.ui.theme.TopAppBarBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            ExpenseManagerTheme {

                var showBottomBar by rememberSaveable { mutableStateOf(true) }
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()

                showBottomBar = when(backStackEntry?.destination?.route){
                    "settings/categories" -> false
                     else -> true
                }

                Scaffold(
                    bottomBar = {
                        if (showBottomBar){

                            NavigationBar(containerColor = TopAppBarBackground) {
                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "expenses",
                                    onClick = { navController.navigate("expenses") },
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.upload),
                                            contentDescription = "Upload"
                                        )
                                    },
                                    label = {
                                        Text(text = "Expenses")
                                    }
                                )

                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "reports",
                                    onClick = { navController.navigate("reports") },
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.bar_chart),
                                            contentDescription = "Reports"
                                        )
                                    },
                                    label = {
                                        Text(text = "Reports")
                                    }
                                )

                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route == "add",
                                    onClick = { navController.navigate("add") },
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.add),
                                            contentDescription = "Add"
                                        )
                                    },
                                    label = {
                                        Text(text = "Add")
                                    }
                                )

                                NavigationBarItem(
                                    selected = backStackEntry?.destination?.route?.startsWith("settings")?:false,
                                    onClick = { navController.navigate("settings") },
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.outline_settings),
                                            contentDescription = "Settings"
                                        )
                                    },
                                    label = {
                                        Text(text = "Settings")
                                    }
                                )
                            }
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
                                    Expenses(navController)
                                }
                            }
                            composable("reports") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Reports(navController)
                                }
                            }
                            composable("add") {
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding),
                                ) {
                                    Add(navController)
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
                                    Categories(navController)
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