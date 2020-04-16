package ru.pg13lac.nbanews.presentation.ui.team_list

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.team_item.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.TeamNameWithId
import ru.pg13lac.nbanews.common.inflate
import ru.pg13lac.nbanews.common.setTeamImage
import ru.pg13lac.nbanews.domain.entity.OnClickCallback
import ru.pg13lac.nbanews.domain.entity.OnClickTeamCallback
import ru.pg13lac.nbanews.presentation.ui.base.BaseAdapter
import ru.pg13lac.nbanews.presentation.ui.base.BaseViewHolder
import javax.inject.Inject

class TeamListAdapter @Inject constructor() : BaseAdapter<TeamNameWithId>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TeamNameWithId> = parent.inflate(R.layout.team_item).let(::ViewHolder)

    private var callback: OnClickTeamCallback? = null

    fun attachCallback(callback: OnClickTeamCallback) {
        this.callback = callback
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<TeamNameWithId>(itemView) {
        override fun bind(model: TeamNameWithId) {
            with(itemView) {
                itemView.setOnClickListener {
                    callback?.routeTo(model)
                }
                tvTeamNameList.text = model.name
                setTeamImage(model.ref, ivTeamList)
            }
        }
    }

}