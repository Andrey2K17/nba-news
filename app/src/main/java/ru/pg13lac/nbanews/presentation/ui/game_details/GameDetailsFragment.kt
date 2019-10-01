package ru.pg13lac.nbanews.presentation.ui.game_details

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_game_details.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.GameDetailsInfo
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.ui.box_score.BoxScoreFragment
import ru.pg13lac.nbanews.presentation.ui.summary.SummaryFragment

class GameDetailsFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_game_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameInfo = arguments?.getParcelable("gameInfo") as GameDetailsInfo

        val adapter = GameDetailsViewPagerAdapter(childFragmentManager)

        adapter.addFragment(SummaryFragment(), getString(R.string.summary))
        adapter.addFragment(
            BoxScoreFragment.newInstance(gameInfo),
            getString(R.string.box_score)
        )
        vpGameDetails.adapter = adapter
        tlGameDetails.setupWithViewPager(vpGameDetails)
    }
}
