package com.emmanuel_yegon.expensemanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(

    primary = Primary,
    onPrimary = TextPrimary,
    background = Surface,
    surface = Surface,
    onSecondary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = Destructive


    /* Other default colors to override
   background = Color(0xFFFFFBFE),
   surface = Color(0xFFFFFBFE),
   onPrimary = Color.White,
   onSecondary = Color.White,
   onTertiary = Color.White,
   onBackground = Color(0xFF1C1B1F),
   onSurface = Color(0xFF1C1B1F),
   */
)

@Composable
fun ExpenseManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    //dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme


    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}