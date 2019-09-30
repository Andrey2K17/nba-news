package ru.pg13lac.nbanews.presentation.ui.game_list

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game_item.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.GlideApp
import ru.pg13lac.nbanews.common.inflate
import ru.pg13lac.nbanews.domain.entity.GameItem
import kotlin.properties.Delegates

class GameListAdapter : RecyclerView.Adapter<GameListAdapter.ViewHolder>() {
    var gameList: List<GameItem> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    private fun setImage(team: String, imageView: ImageView) {
        GlideApp.with(imageView.context)
            .load("https://stats.nba.com/media/img/teams/logos/${team}_logo.svg")
            .centerCrop()
            .into(imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        parent.inflate(R.layout.game_item).let(::ViewHolder)

    override fun getItemCount(): Int = gameList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: GameItem) {
            with(itemView) {
                tvGameStatus.text = game.game_status
                tvLeftTeamName.text = game.left_team_name
                tvLeftTeamPts.text = game.left_team_pts
                tvRightTeamName.text = game.right_team_name
                tvRightTeamPts.text = game.right_team_pts
                ivRightTeam.setImageResource(R.drawable.ic_game_list)
                setImage(game.left_img, ivLeftTeam)
                setImage(game.right_img, ivRightTeam)
            }
        }
    }
}