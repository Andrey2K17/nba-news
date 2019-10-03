package ru.pg13lac.nbanews.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import ru.pg13lac.nbanews.R

class GameLeaders @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.game_leaders, this, true)
    }
}