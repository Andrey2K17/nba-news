package ru.pg13lac.nbanews.common

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import ru.pg13lac.nbanews.App
import ru.pg13lac.nbanews.R

fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun setTeamImage(team: String, imageView: ImageView) {
    GlideApp.with(imageView.context)
        .load("https://stats.nba.com/media/img/teams/logos/${team}_logo.svg")
        .centerCrop()
        .into(imageView)
}

fun setPlayerImage(name: String, imageView: ImageView) {
    val newName = name.toLowerCase().trim().replace(" ", "-")
    GlideApp.with(imageView.context)
        .load("https://allbasketball.org/templates/AB2014/images/players/260x190/${newName}.png")
        .apply(RequestOptions.centerCropTransform())
        .placeholder(R.drawable.ic_person)
        .into(imageView)
}

var View.isVisible: Boolean
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.GONE
    }
    get() = this.visibility == View.VISIBLE