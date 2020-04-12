package ru.pg13lac.nbanews.presentation.ui.game_details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.fragment_game_details.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.ui.box_score.BoxScoreFragment
import ru.pg13lac.nbanews.presentation.ui.summary.SummaryFragment
import javax.inject.Inject

class GameDetailsFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_game_details

    @Inject
    lateinit var summaryFragment: SummaryFragment

    @Inject
    lateinit var boxScoreFragment: BoxScoreFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = arguments?.getString("gameId")

        summaryFragment.arguments = bundleOf("gameId" to gameId)
        boxScoreFragment.arguments = arguments

        val adapter = GameDetailsViewPagerAdapter(childFragmentManager)

        adapter.addFragment(summaryFragment, getString(R.string.summary))
        adapter.addFragment(boxScoreFragment, getString(R.string.box_score))

        vpGameDetails.adapter = adapter
        tlGameDetails.setupWithViewPager(vpGameDetails)
    }
}
