package com.emmanuel_yegon.expensemanager.components.charts

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.emmanuel_yegon.expensemanager.models.Recurrence
import com.emmanuel_yegon.expensemanager.ui.theme.SystemGray04
import com.github.tehras.charts.bar.BarChartData

class BarDrawer constructor(recurrence: Recurrence) :
    com.github.tehras.charts.bar.renderer.bar.BarDrawer {
    private val barPaint = Paint().apply {
        this.isAntiAlias = true
    }

    private  val rightOffSet = when(recurrence){
        Recurrence.Weekly -> 24f
        Recurrence.Monthly -> 6f
        Recurrence.Yearly -> 18f
        else -> 0f
    }

    override fun drawBar(
        drawScope: DrawScope,
        canvas: Canvas,
        barArea: Rect,
        bar: BarChartData.Bar,
    ) {
        canvas.drawRoundRect(
            barArea.left,
            0f,
            barArea.right + rightOffSet,
            barArea.bottom,
            16f,
            16f,
            barPaint.apply {
                color = SystemGray04
            })

        canvas.drawRoundRect(
            barArea.left,
            barArea.top,
            barArea.right + rightOffSet,
            barArea.bottom,
            16f,
            16f,
            barPaint.apply {
                color = bar.color
            })
    }

}