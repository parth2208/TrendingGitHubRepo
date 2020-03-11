package com.parthbhardwaj.trendinggithubrepo.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.lang.Exception

class CircularView : AppCompatTextView {
    private var strokeWidth: Float = 0.toFloat()
    internal var strokeColor: Int = 0
    internal var solidColor: Int = 0

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun draw(canvas: Canvas) {

        val circlePaint = Paint()
        circlePaint.color = solidColor
        circlePaint.flags = Paint.ANTI_ALIAS_FLAG

        val strokePaint = Paint()
        strokePaint.color = strokeColor
        strokePaint.flags = Paint.ANTI_ALIAS_FLAG

        val h = this.height
        val w = this.width

        val diameter = if (h > w) h else w
        val radius = diameter / 2

        this.height = diameter
        this.width = diameter

        canvas.drawCircle(
            (diameter / 2).toFloat(),
            (diameter / 2).toFloat(),
            radius.toFloat(),
            strokePaint
        )

        canvas.drawCircle(
            (diameter / 2).toFloat(),
            (diameter / 2).toFloat(),
            radius - strokeWidth,
            circlePaint
        )

        super.draw(canvas)
    }

    fun setSolidColor(color: String) {
        try {
            solidColor = Color.parseColor(color)
        } catch (ex: Exception){
            //TODO:
        }
    }
}