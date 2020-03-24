package ru.pg13lac.nbanews.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.more_item.view.*
import ru.pg13lac.nbanews.R

class MoreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.more_item, this, true)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MoreView)
        ivMoreIcon.setImageDrawable(attributes.getDrawable(R.styleable.MoreView_image))
        tvMoreTitle.text = attributes.getString(R.styleable.MoreView_text)
        attributes.recycle()
    }
}