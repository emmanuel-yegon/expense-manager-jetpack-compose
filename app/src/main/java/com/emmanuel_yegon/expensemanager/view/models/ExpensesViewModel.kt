package com.emmanuel_yegon.expensemanager.view.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmanuel_yegon.expensemanager.mock.mockExpenses
import com.emmanuel_yegon.expensemanager.models.Expense
import com.emmanuel_yegon.expensemanager.models.Recurrence
import com.emmanuel_yegon.expensemanager.utils.calculateDateRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ExpensesState(

    val recurrence: Recurrence= Recurrence.Daily,
    val sumTotal: Double = 1243.82,
    val expenses: List<Expense> = mockExpenses


)

class ExpensesViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(ExpensesState())
    val uiState: StateFlow<ExpensesState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            setRecurrence(Recurrence.Daily)
        }
    }
    fun setRecurrence(recurrence: Recurrence){
        val (start,end) = calculateDateRange(recurrence,0)

        val filteredExpenses = mockExpenses.filter { expense ->
            (expense.date.toLocalDate().isAfter(start) && expense.date.toLocalDate()
                .isBefore(end))
                    || expense.date.toLocalDate().isEqual(start) || expense.date.toLocalDate()
                .isEqual(end)
        }

        val sumTotal= filteredExpenses.sumOf { it.amount }

        _uiState.update { currentState ->
            currentState.copy(
                recurrence = recurrence,
                expenses = filteredExpenses,
                sumTotal = sumTotal
            )
        }
    }

}