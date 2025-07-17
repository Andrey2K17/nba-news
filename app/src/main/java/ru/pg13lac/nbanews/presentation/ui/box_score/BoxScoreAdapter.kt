package ru.pg13lac.nbanews.presentation.ui.box_score

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.pg13lac.nbanews.common.setPlayerImage
import ru.pg13lac.nbanews.databinding.BoxScoreItemBinding
import ru.pg13lac.nbanews.domain.entity.BoxScore
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder
import javax.inject.Inject

class BoxScoreAdapter @Inject constructor() : BaseAdapter<BoxScore>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BoxScore> {
        val binding = BoxScoreItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        private val binding: BoxScoreItemBinding
    ) : BaseViewHolder<BoxScore>(binding.root) {

        override fun bind(model: BoxScore) {
            with(binding) {
                model.player_id?.let { setPlayerImage(it, ivPlayerImage) }
                tvPlayerName.text = model.player_name.orEmpty()
                tvPlayerSurname.text = model.player_surname.orEmpty()
                tvPlayerPoints.text = model.player_pts.orEmpty()
                tvPlayerRebounds.text = model.player_reb.orEmpty()
                tvPlayerAssist.text = model.player_ast.orEmpty()
            }
        }
    }
}