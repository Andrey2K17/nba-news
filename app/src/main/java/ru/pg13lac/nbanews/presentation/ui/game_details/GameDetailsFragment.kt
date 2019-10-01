package ru.pg13lac.nbanews.presentation.ui.game_details


import android.os.Bundle
import android.view.View
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment

class GameDetailsFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_game_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = arguments?.getString("gameId")
    }
}
