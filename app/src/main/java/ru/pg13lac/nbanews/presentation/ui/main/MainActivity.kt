package ru.pg13lac.nbanews.presentation.ui.main

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.presentation.ui.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.main_host_fragment)
        NavigationUI.setupWithNavController(bnvMain, navController)
    }
}
