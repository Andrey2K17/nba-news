package ru.pg13lac.nbanews.presentation.ui.box_score

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.box_score_item.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.inflate
import ru.pg13lac.nbanews.common.setPlayerImage
import ru.pg13lac.nbanews.domain.entity.BoxScore
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder

class BoxScoreAdapter : BaseAdapter<BoxScore>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BoxScore> =
        parent.inflate(R.layout.box_score_item).let(::ViewHolder)

    inner class ViewHolder(itemView: View) : BaseViewHolder<BoxScore>(itemView) {
        override fun bind(model: BoxScore) {
            with(itemView) {
                setPlayerImage(model.player_id, ivPlayerImage)
                tvPlayerName.text = model.player_name
                tvPlayerSurname.text = model.player_surname
                tvPlayerPoints.text = model.player_pts
                tvPlayerRebounds.text = model.player_reb
                tvPlayerAssist.text = model.player_ast
            }
        }
    }
}