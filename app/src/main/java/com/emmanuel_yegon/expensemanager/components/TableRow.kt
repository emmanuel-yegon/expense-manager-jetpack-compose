package com.emmanuel_yegon.expensemanager.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emmanuel_yegon.expensemanager.R
import com.emmanuel_yegon.expensemanager.ui.theme.Destructive
import com.emmanuel_yegon.expensemanager.ui.theme.TextPrimary
import com.emmanuel_yegon.expensemanager.ui.theme.Typography

@Composable
fun TableRow(
    label: String,
    onClick: (String) -> Unit,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false
) {
    val textColor = if (isDestructive) Destructive else TextPrimary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(label) }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, style = Typography.bodyMedium, color = textColor)

        if (hasArrow) {
            Icon(
                painter = painterResource(id = R.drawable.outline_chevron),
                contentDescription = "Right arrow"
            )
        }

    }

}