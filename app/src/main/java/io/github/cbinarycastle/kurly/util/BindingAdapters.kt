package io.github.cbinarycastle.kurly.util

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.cbinarycastle.kurly.R
import io.github.cbinarycastle.kurly.domain.model.SectionType
import kotlin.math.round

@BindingAdapter("strikethrough")
fun setStrikethrough(textView: TextView, enabled: Boolean) {
    textView.paintFlags = if (enabled) {
        textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("percentage")
fun setPercentage(textView: TextView, value: Double) {
    val percentageFormat = textView.context.getString(R.string.percentage_format)
    textView.text = String.format(percentageFormat, round(value * 100))
}

@BindingAdapter("price")
fun setPrice(textView: TextView, value: Int) {
    val priceFormat = textView.context.getString(R.string.price_format)
    textView.text = String.format(priceFormat, value)
}

@BindingAdapter("sectionType")
fun setLayoutManager(recyclerView: RecyclerView, sectionType: SectionType) {
    recyclerView.layoutManager = when (sectionType) {
        SectionType.VERTICAL -> LinearLayoutManager(recyclerView.context)
        SectionType.HORIZONTAL -> {
            LinearLayoutManager(recyclerView.context).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }
        SectionType.GRID -> GridLayoutManager(recyclerView.context, 3)
    }
}