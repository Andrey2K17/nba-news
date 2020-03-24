package ru.pg13lac.nbanews.presentation.ui.more

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_more.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment

class MoreFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_more

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mvSelectFavoriteTeam.setOnClickListener {
            findNavController().navigate(R.id.actionToFavoriteTeam)
        }
    }
}
