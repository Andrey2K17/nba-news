package ru.pg13lac.nbanews.presentation.ui.team_profile

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.player_item.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.inflate
import ru.pg13lac.nbanews.common.setPlayerImage
import ru.pg13lac.nbanews.domain.entity.OnClickPlayerCallback
import ru.pg13lac.nbanews.domain.entity.teamProfile.Player
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder
import javax.inject.Inject

class RosterAdapter @Inject constructor() : BaseAdapter<Player>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Player> =
        parent.inflate(R.layout.player_item).let(::ViewHolder)

    private var callback: OnClickPlayerCallback? = null

    fun attachCallback(callback: OnClickPlayerCallback) {
        this.callback = callback
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<Player>(itemView) {
        override fun bind(model: Player) {
            with(itemView) {
                itemView.setOnClickListener {
                    callback?.routeTo(model)
                }
                setPlayerImage(model.reference, ivPlayerItem)
                tvPlayerItemName.text = model.full_name
                tvPlayerItemNumber.text = model.jersey_number
                tvPlayerItemPosition.text = model.position
            }
        }
    }
}