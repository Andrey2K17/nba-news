package ru.pg13lac.nbanews.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.databinding.MoreItemBinding

class MoreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: MoreItemBinding

    init {
        // Инициализация View Binding
        binding = MoreItemBinding.inflate(LayoutInflater.from(context), this, true)

        // Обработка кастомных атрибутов
        context.obtainStyledAttributes(attrs, R.styleable.MoreView).use { attributes ->
            binding.ivMoreIcon.setImageDrawable(attributes.getDrawable(R.styleable.MoreView_image))
            binding.tvMoreTitle.text = attributes.getString(R.styleable.MoreView_text)
        }
    }

    // Дополнительные методы для управления view, если необходимо
    fun setIcon(drawableRes: Int) {
        binding.ivMoreIcon.setImageResource(drawableRes)
    }

    fun setTitle(text: String) {
        binding.tvMoreTitle.text = text
    }
}