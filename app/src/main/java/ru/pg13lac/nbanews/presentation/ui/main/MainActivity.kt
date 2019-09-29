package ru.pg13lac.nbanews.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.presentation.ui.game_list.GameListFragment
import ru.pg13lac.nbanews.presentation.ui.settings.SettingsFragment
import ru.pg13lac.nbanews.presentation.ui.teams_table.TeamsTableFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnvMain.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addFragment(GameListFragment())
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_games_list -> {
                    addFragment(GameListFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_teams_table -> {
                    addFragment(TeamsTableFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_setting -> {
                    addFragment(SettingsFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.flContent, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
