package com.emmanuel_yegon.expensemanager.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emmanuel_yegon.expensemanager.R
import com.emmanuel_yegon.expensemanager.ui.theme.ExpenseManagerTheme
import com.emmanuel_yegon.expensemanager.ui.theme.FillTertiary
import com.emmanuel_yegon.expensemanager.ui.theme.Shapes
import com.emmanuel_yegon.expensemanager.ui.theme.Typography

@Composable
fun PickerTrigger(
    label: String ="",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
  Surface(
      shape = Shapes.medium,
      color = FillTertiary,
      modifier = modifier,
      onClick = onClick
  ) {
      Row (
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 20.dp, vertical = 3.dp)
      ){
          Text(label, style= Typography.titleSmall)
          Icon(
              painter = painterResource(id = R.drawable.ic_unfold_more),
              contentDescription = "Open picker",
              modifier=Modifier.padding(start=10.dp)
          )
      }
  }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PickerTriggerPreview(){
    ExpenseManagerTheme {
        PickerTrigger("this week", onClick = {})
    }
}