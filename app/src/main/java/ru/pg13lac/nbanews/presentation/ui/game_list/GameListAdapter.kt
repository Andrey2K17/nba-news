package ru.pg13lac.nbanews.presentation.ui.game_list

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.pg13lac.nbanews.common.setTeamImage
import ru.pg13lac.nbanews.databinding.GameItemBinding
import ru.pg13lac.nbanews.domain.entity.GameItem
import ru.pg13lac.nbanews.domain.entity.OnClickCallback
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder
import javax.inject.Inject

class GameListAdapter @Inject constructor() : BaseAdapter<GameItem>() {

    private var callback: OnClickCallback? = null

    fun attachCallback(callback: OnClickCallback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<GameItem> {
        val binding = GameItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        private val binding: GameItemBinding
    ) : BaseViewHolder<GameItem>(binding.root) {

        override fun bind(model: GameItem) {
            with(binding) {
                root.setOnClickListener {
                    callback?.routeTo(
                        model.gameId,
                        model.left_team_name,
                        model.right_team_name
                    )
                }

                tvGameStatus.text = model.game_status.orEmpty()
                tvLeftTeamName.text = model.left_team_name.orEmpty()
                tvLeftTeamPts.text = model.left_team_pts.orEmpty()
                tvRightTeamName.text = model.right_team_name.orEmpty()
                tvRightTeamPts.text = model.right_team_pts.orEmpty()

                model.left_img?.let { setTeamImage(it, ivLeftTeam) }
                model.right_img?.let { setTeamImage(it, ivRightTeam) }
            }
        }
    }
}