package ru.pg13lac.nbanews.presentation.ui.box_score

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.fragment_box_score.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.GameDetailsInfo
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment

class BoxScoreFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_box_score

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameInfo = arguments?.getParcelable("game") as GameDetailsInfo

        tlBoxScore.getTabAt(0)?.text = gameInfo.leftTeam
        tlBoxScore.getTabAt(1)?.text = gameInfo.rightTeam
    }

    companion object {
        fun newInstance(gameInfo: GameDetailsInfo): BoxScoreFragment {
            val fragment = BoxScoreFragment()
            val bundle = bundleOf("game" to gameInfo)
            fragment.arguments = bundle
            return fragment
        }
    }
}
