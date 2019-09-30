package ru.pg13lac.nbanews.presentation.ui.game_list

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.game_item.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.GlideApp
import ru.pg13lac.nbanews.common.inflate
import ru.pg13lac.nbanews.domain.entity.GameItem
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder

class GameListAdapter : BaseAdapter<GameItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<GameItem> =
        parent.inflate(R.layout.game_item).let(::ViewHolder)

    private fun setImage(team: String, imageView: ImageView) {
        GlideApp.with(imageView.context)
            .load("https://stats.nba.com/media/img/teams/logos/${team}_logo.svg")
            .centerCrop()
            .into(imageView)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<GameItem>(itemView) {
        override fun bind(model: GameItem) {
            with(itemView) {
                tvGameStatus.text = model.game_status
                tvLeftTeamName.text = model.left_team_name
                tvLeftTeamPts.text = model.left_team_pts
                tvRightTeamName.text = model.right_team_name
                tvRightTeamPts.text = model.right_team_pts
                setImage(model.left_img, ivLeftTeam)
                setImage(model.right_img, ivRightTeam)
            }
        }
    }

}