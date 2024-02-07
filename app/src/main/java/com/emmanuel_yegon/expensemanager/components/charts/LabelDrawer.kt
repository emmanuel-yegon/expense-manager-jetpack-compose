package com.emmanuel_yegon.expensemanager.components.charts


import android.graphics.Paint
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import com.emmanuel_yegon.expensemanager.models.Recurrence
import com.emmanuel_yegon.expensemanager.ui.theme.LabelSecondary
import com.github.tehras.charts.piechart.utils.toLegacyInt

class LabelDrawer(val recurrence: Recurrence, val lastDay: Int? = -1) :
    com.github.tehras.charts.bar.renderer.label.LabelDrawer {

    private val leftOffSet = when (recurrence) {
        Recurrence.Weekly -> 50f
        Recurrence.Monthly -> 13f
        Recurrence.Yearly -> 32f
        else -> 0f
    }


    private val paint = Paint().apply {
        this.textAlign = Paint.Align.CENTER
        this.color = LabelSecondary.toLegacyInt()
        this.textSize = 42f
    }

    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect,
    ) {
        val monthlyCondition =
            recurrence == Recurrence.Monthly && (
                Integer.parseInt(label) % 5 == 0 ||
                Integer.parseInt(label) == 1 ||
                Integer.parseInt(label) == lastDay
            )
        if (monthlyCondition || recurrence != Recurrence.Monthly)
            canvas.nativeCanvas.drawText(
                label,
                barArea.left + leftOffSet,
                barArea.bottom + 65f,
                paint
            )
    }

}