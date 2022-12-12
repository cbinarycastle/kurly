package io.github.cbinarycastle.kurly.util

import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import io.github.cbinarycastle.kurly.R
import kotlin.math.roundToInt

@BindingAdapter("visible")
fun setVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

@BindingAdapter("invisible")
fun setInvisible(view: View, isInvisible: Boolean) {
    view.isInvisible = isInvisible
}

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
    textView.text = String.format(percentageFormat, (value * 100).roundToInt())
}

@BindingAdapter("price")
fun setPrice(textView: TextView, value: Int) {
    val priceFormat = textView.context.getString(R.string.price_format)
    textView.text = String.format(priceFormat, value)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String) {
    imageView.load(imageUrl)
}