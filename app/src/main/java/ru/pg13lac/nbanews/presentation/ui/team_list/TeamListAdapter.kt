package ru.pg13lac.nbanews.presentation.ui.team_list

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.pg13lac.nbanews.common.TeamNameWithId
import ru.pg13lac.nbanews.common.setTeamImage
import ru.pg13lac.nbanews.databinding.TeamItemBinding
import ru.pg13lac.nbanews.domain.entity.OnClickTeamCallback
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder
import javax.inject.Inject

class TeamListAdapter @Inject constructor() : BaseAdapter<TeamNameWithId>() {

    private var callback: OnClickTeamCallback? = null

    fun attachCallback(callback: OnClickTeamCallback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TeamNameWithId> {
        val binding = TeamItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    inner class ViewHolder(
        private val binding: TeamItemBinding
    ) : BaseViewHolder<TeamNameWithId>(binding.root) {

        override fun bind(model: TeamNameWithId) {
            with(binding) {
                root.setOnClickListener { callback?.routeTo(model) }
                tvTeamNameList.text = model.name
                model.ref?.let { setTeamImage(it, ivTeamList) }
            }
        }
    }
}