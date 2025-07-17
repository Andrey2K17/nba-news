package ru.pg13lac.nbanews.presentation.ui.team_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.pg13lac.nbanews.common.setPlayerImage
import ru.pg13lac.nbanews.databinding.PlayerItemBinding
import ru.pg13lac.nbanews.domain.entity.OnClickPlayerCallback
import ru.pg13lac.nbanews.domain.entity.teamProfile.Player
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder
import javax.inject.Inject

class RosterAdapter @Inject constructor() : BaseAdapter<Player>() {

    private var callback: OnClickPlayerCallback? = null

    fun attachCallback(callback: OnClickPlayerCallback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Player> {
        val binding = PlayerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        private val binding: PlayerItemBinding
    ) : BaseViewHolder<Player>(binding.root) {

        override fun bind(model: Player) {
            with(binding) {
                root.setOnClickListener { callback?.routeTo(model) }
                model.reference?.let { setPlayerImage(it, ivPlayerItem) }
                tvPlayerItemName.text = model.full_name.orEmpty()
                tvPlayerItemNumber.text = model.jersey_number.orEmpty()
                tvPlayerItemPosition.text = model.position.orEmpty()
            }
        }
    }
}