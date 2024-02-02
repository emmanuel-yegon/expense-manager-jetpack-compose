package com.emmanuel_yegon.expensemanager.pages

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emmanuel_yegon.expensemanager.R
import com.emmanuel_yegon.expensemanager.components.TableRow
import com.emmanuel_yegon.expensemanager.components.UnstyledTextField
import com.emmanuel_yegon.expensemanager.ui.theme.BackgroundElevated
import com.emmanuel_yegon.expensemanager.ui.theme.Destructive
import com.emmanuel_yegon.expensemanager.ui.theme.DividerColor
import com.emmanuel_yegon.expensemanager.ui.theme.ExpenseManagerTheme
import com.emmanuel_yegon.expensemanager.ui.theme.Shapes
import com.emmanuel_yegon.expensemanager.ui.theme.TopAppBarBackground
import com.emmanuel_yegon.expensemanager.ui.theme.Typography
import com.emmanuel_yegon.expensemanager.view.models.CategoriesViewModel
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Categories(navController: NavController, vm: CategoriesViewModel= viewModel()){

    val uiState by vm.uiState.collectAsState()

    val colorPickerController = rememberColorPickerController()


    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Categories") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground
                ),
                navigationIcon = {
                    Surface(
                        onClick = navController::popBackStack,
                        color = Color.Transparent
                    ) {
                        Row (modifier=Modifier.padding(vertical = 10.dp)){
                            Icon(
                                Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                contentDescription = "Settings"
                            )
                            Text(text = "Settings")
                        }
                    }
                }

            )
        },

        content = { innerPadding ->
            Column(modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier=Modifier.weight(1f)) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(Shapes.medium)
//                            .background(BackgroundElevated)
                            .fillMaxWidth()
                    ) {
                        itemsIndexed(
                            uiState.categories,
                            key={_,category -> category.name}) {index, category->
                            SwipeableActionsBox(
                                endActions = listOf(
                                    SwipeAction(
                                        icon = painterResource(id = R.drawable.delete),
                                        background = Destructive,
                                        onSwipe = {vm.deleteCategory(category)}
                                    ),
                                ),
                                modifier = Modifier.animateItemPlacement()
                            ){
                                TableRow(modifier=Modifier.background(BackgroundElevated)){
                                    Row(verticalAlignment = Alignment.CenterVertically, modifier=Modifier.padding(horizontal = 16.dp)){
                                        Surface (
                                            color = category.color,
                                            shape = CircleShape,
                                            border= BorderStroke(
                                                width = 2.dp,
                                                color= Color.White
                                            ),
                                            modifier = Modifier.size(16.dp)
                                        ){}
                                        Text(
                                            text = category.name,
                                            modifier=Modifier
                                                .padding(
                                                    horizontal = 16.dp,
                                                    vertical = 10.dp
                                                ),
                                            style = Typography.bodyMedium
                                        )
                                    }
                                }
                            }

                            if(index < uiState.categories.size - 1){
                                Row(modifier = Modifier.background(BackgroundElevated).height(1.dp)){
                                    Divider(
                                        modifier = Modifier.padding(start=16.dp),
                                        thickness = 1.dp,
                                        color = DividerColor
                                    )
                                }

                            }
                        }

                    }
                }

                Row (
                    modifier= Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (uiState.colorPickerShowing) {
                        Dialog(onDismissRequest = vm::hideColorPicker) {
                            Surface(color = BackgroundElevated, shape = Shapes.large) {
                                Column(
                                    modifier = Modifier
                                        .padding(all = 30.dp)
                                ) {
                                    Text(text = "Select a color ", style = Typography.titleLarge)
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 24.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        AlphaTile(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(60.dp)
                                                .clip(RoundedCornerShape(6.dp)),
                                            controller = colorPickerController
                                        )
                                    }
                                    HsvColorPicker(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(300.dp)
                                            .padding(10.dp),
                                        controller = colorPickerController,
                                        onColorChanged = { envelope ->
                                            vm.setNewCategoryColor(envelope.color)
                                        }
                                    )
                                    BrightnessSlider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                            .height(35.dp),
                                        controller = colorPickerController
                                    )
                                    TextButton(
                                        onClick = vm::hideColorPicker,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 24.dp)
                                    ) {
                                        Text(text = "Done")
                                    }
                                }
                            }

                        }
                    }

                    Surface(
                        onClick = vm::showColorPicker,
                        shape = CircleShape,
                        color = uiState.newCategoryColor,
                        border= BorderStroke(
                            width=2.dp,
                            color= Color.White
                        ),
                        modifier = Modifier.size(width = 24.dp, height = 24.dp)
                    ) {}
                    Surface(
                        color= BackgroundElevated,
                        modifier = Modifier
                            .height(44.dp)
                            .weight(1f)
                            .padding(start = 16.dp),
                        shape = Shapes.large
                    ) {
                        Column(verticalArrangement = Arrangement.Center, modifier=Modifier.fillMaxHeight()) {
                            UnstyledTextField(
                                value = uiState.newCategoryName,
                                onValueChange = vm::setNewCategoryName,
                                placeholder= { Text(text = "Category name")},
                                modifier=Modifier.fillMaxWidth(),
                                maxLines = 1
                            )
                        }
                    }
                    IconButton(
                        onClick = vm::createNewCategory,
                        modifier= Modifier
                            .padding(start = 16.dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.Send,
                            contentDescription = "Create new category"
                        )
                    }

                }
            }
        })
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CategoriesPreview(){
    ExpenseManagerTheme {
        Categories(navController = rememberNavController())
    }
}