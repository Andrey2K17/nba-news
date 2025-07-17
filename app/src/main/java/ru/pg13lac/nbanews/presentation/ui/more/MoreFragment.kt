package ru.pg13lac.nbanews.presentation.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.databinding.FragmentMoreBinding
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment

class MoreFragment : BaseFragment<FragmentMoreBinding>(R.layout.fragment_more) {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMoreBinding {
        return FragmentMoreBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mvSelectFavoriteTeam.setOnClickListener {
            findNavController().navigate(R.id.actionToFavoriteTeam)
        }
    }
}
