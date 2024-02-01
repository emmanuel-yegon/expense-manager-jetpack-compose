package com.emmanuel_yegon.expensemanager.view.models

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.emmanuel_yegon.expensemanager.models.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class CategoriesState(
    val newCategoryColor: Color= Color.White,
    val newCategoryName: String="",
    val colorPickerShowing: Boolean=false,
    val categories : MutableList<Category> = mutableListOf(
        Category("Groceries", Color.Cyan),
        Category("Bills", Color.Yellow),
        Category("Subscriptions", Color.Red),
        Category("Take out", Color.Gray),

    )
)

class CategoriesViewModel:ViewModel (){
    private val _uiState = MutableStateFlow(CategoriesState())
    val uiState: StateFlow<CategoriesState> = _uiState.asStateFlow()

    fun setNewCategoryColor(color: Color){
        _uiState.update { currentState ->
            currentState.copy(
                newCategoryColor = color
            )
        }
    }

    fun setNewCategoryName(name: String){
        _uiState.update { currentState ->
            currentState.copy(
                newCategoryName = name
            )
        }
    }

    fun showColorPicker(){
        _uiState.update { currentState ->
            currentState.copy(
                colorPickerShowing = true
            )
        }
    }

    fun hideColorPicker(){
        _uiState.update { currentState ->
            currentState.copy(
                colorPickerShowing = false
            )
        }
    }

    fun createNewCategory(){
        val newCategoriesList = mutableListOf(
            Category(
                _uiState.value.newCategoryName,
                _uiState.value.newCategoryColor
            )
        )
            newCategoriesList.addAll(
            _uiState.value.categories
        )
        _uiState.update { currentState ->
            currentState.copy(
                categories = newCategoriesList,
                newCategoryName = "",
                newCategoryColor = Color.White
            )
        }
    }

}