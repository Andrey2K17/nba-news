package ru.pg13lac.nbanews.presentation.ui.teams_table

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.databinding.FragmentTeamsTableBinding
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment

class TeamsTableFragment : BaseFragment<FragmentTeamsTableBinding>(R.layout.fragment_teams_table) {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTeamsTableBinding {
        return FragmentTeamsTableBinding.inflate(inflater, container, false)
    }
}
