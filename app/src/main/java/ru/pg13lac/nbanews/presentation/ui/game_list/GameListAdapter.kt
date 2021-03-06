package ru.pg13lac.nbanews.presentation.ui.game_list

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.game_item.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.inflate
import ru.pg13lac.nbanews.common.setTeamImage
import ru.pg13lac.nbanews.domain.entity.GameItem
import ru.pg13lac.nbanews.domain.entity.OnClickCallback
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder
import javax.inject.Inject

class GameListAdapter @Inject constructor() : BaseAdapter<GameItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<GameItem> =
        parent.inflate(R.layout.game_item).let(::ViewHolder)

    private var callback: OnClickCallback? = null

    fun attachCallback(callback: OnClickCallback) {
        this.callback = callback
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<GameItem>(itemView) {
        override fun bind(model: GameItem) {
            with(itemView) {
                itemView.setOnClickListener {
                    callback?.routeTo(model.gameId, model.left_team_name, model.right_team_name)
                }
                tvGameStatus.text = model.game_status
                tvLeftTeamName.text = model.left_team_name
                tvLeftTeamPts.text = model.left_team_pts
                tvRightTeamName.text = model.right_team_name
                tvRightTeamPts.text = model.right_team_pts
                setTeamImage(model.left_img, ivLeftTeam)
                setTeamImage(model.right_img, ivRightTeam)
            }
        }
    }
}