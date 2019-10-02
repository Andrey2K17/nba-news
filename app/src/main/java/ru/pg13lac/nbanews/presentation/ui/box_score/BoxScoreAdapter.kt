package ru.pg13lac.nbanews.presentation.ui.box_score

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.box_score_item.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.GlideApp
import ru.pg13lac.nbanews.common.inflate
import ru.pg13lac.nbanews.domain.entity.BoxScore
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder

class BoxScoreAdapter : BaseAdapter<BoxScore>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BoxScore> =
        parent.inflate(R.layout.box_score_item).let(::ViewHolder)

    private fun setImage(playerId: String, imageView: ImageView) {
        GlideApp.with(imageView.context)
            .load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/${playerId}.png")
            .apply(RequestOptions.centerCropTransform())
            .placeholder(R.drawable.ic_person)
            .into(imageView)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<BoxScore>(itemView) {
        override fun bind(model: BoxScore) {
            with(itemView) {
                setImage(model.player_id, ivPlayerImage)
                tvPlayerName.text = model.player_name
                tvPlayerSurname.text = model.player_surname
                tvPlayerPoints.text = model.player_pts
                tvPlayerRebounds.text = model.player_reb
                tvPlayerAssist.text = model.player_ast
            }
        }
    }
}