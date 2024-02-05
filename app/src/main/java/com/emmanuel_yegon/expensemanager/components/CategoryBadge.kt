package com.emmanuel_yegon.expensemanager.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emmanuel_yegon.expensemanager.models.Category
import com.emmanuel_yegon.expensemanager.ui.theme.Shapes
import com.emmanuel_yegon.expensemanager.ui.theme.Typography

@Composable
fun CategoryBadge(category: Category, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = Shapes.large,
        color = category.color.copy(alpha = 0.25f)
    ) {
        Text(
            category.name,
            color = category.color,
            style = Typography.bodySmall,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}