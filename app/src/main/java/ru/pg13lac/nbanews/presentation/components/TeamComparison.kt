package ru.pg13lac.nbanews.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.setTeamImage
import ru.pg13lac.nbanews.databinding.TeamComparisonBinding
import ru.pg13lac.nbanews.domain.entity.GameDetails
import ru.pg13lac.nbanews.domain.entity.Pair
import ru.pg13lac.nbanews.domain.entity.Shots

class TeamComparison @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: TeamComparisonBinding
    init {
        binding = TeamComparisonBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setTeamComparisonView(gameDetails: GameDetails?) {
        gameDetails?.teamComparison?.first?.TEAM_ABBREVIATION?.let {
            setTeamImage(
                it,
                binding.ivLeftTeamComparison
            )
        }
        gameDetails?.teamComparison?.second?.TEAM_ABBREVIATION?.let {
            setTeamImage(
                it,
                binding.ivRightTeamComparison
            )
        }
        gameDetails?.teamComparison?.let {
            val (first, second) = it
            binding.clFieldFoals.setView(
                Pair(
                    with(first) { Shots(FGM, FGA, FG_PCT) },
                    with(second) { Shots(FGM, FGA, FG_PCT) }
                ),
                context.getString(R.string.field_goals)
            )

            binding.cl3Pointers.setView(
                Pair(
                    with(first) { Shots(FG3M, FG3A, FG3_PCT) },
                    with(second) { Shots(FG3M, FG3A, FG3_PCT) }
                ),
                context.getString(R.string.three_pointers)
            )

            binding.clFreeThrows.setView(
                Pair(
                    with(first) { Shots(FTM, FTA, FT_PCT) },
                    with(second) { Shots(FTM, FTA, FT_PCT) }
                ),
                context.getString(R.string.free_throws)
            )

            binding.clAssists.setView(
                Pair(Shots(first.AST), Shots(second.AST)),
                context.getString(R.string.assists)
            )

            binding.clTotalRebounds.setView(
                Pair(Shots(first.REB), Shots(second.REB)),
                context.getString(R.string.total_rebounds)
            )

            binding.clOffensiveRebounds.setView(
                Pair(Shots(first.O_REB), Shots(second.O_REB)),
                context.getString(R.string.offensive_rebounds)
            )

            binding.clDefensiveRebounds.setView(
                Pair(Shots(first.D_REB), Shots(second.D_REB)),
                context.getString(R.string.defensive_rebounds)
            )

            binding.clSteals.setView(
                Pair(Shots(first.STL), Shots(second.STL)),
                context.getString(R.string.steals)
            )

            binding.clBlocks.setView(
                Pair(Shots(first.BLK), Shots(second.BLK)),
                context.getString(R.string.blocks)
            )

            binding.clTurnovers.setView(
                Pair(Shots(first.TO), Shots(second.TO)),
                context.getString(R.string.turnovers)
            )
        }
    }
}