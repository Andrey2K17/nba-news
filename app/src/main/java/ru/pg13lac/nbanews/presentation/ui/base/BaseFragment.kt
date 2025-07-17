package ru.pg13lac.nbanews.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import ru.pg13lac.nbanews.common.inflate

abstract class BaseFragment<VB : ViewBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = createBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setModelBindings()
        setListeners()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    open fun setListeners() {}

    open fun setModelBindings() {}

    open fun showMessage(resMsg: Int) {
        val view = view ?: return
        Snackbar.make(view, resMsg, Snackbar.LENGTH_SHORT).show()
    }
}