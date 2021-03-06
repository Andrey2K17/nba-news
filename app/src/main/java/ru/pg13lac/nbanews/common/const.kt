package ru.pg13lac.nbanews.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

val nameTeams = mapOf(
    "1610612737" to "Hawks",
    "1610612738" to "Celtics",
    "1610612751" to "Nets",
    "1610612766" to "Hornets",
    "1610612741" to "Bulls",
    "1610612739" to "Cavaliers",
    "1610612742" to "Mavericks",
    "1610612743" to "Nuggets",
    "1610612765" to "Pistons",
    "1610612744" to "Warriors",
    "1610612745" to "Rockets",
    "1610612754" to "Pacers",
    "1610612746" to "Clippers",
    "1610612747" to "Lakers",
    "1610612763" to "Grizzlies",
    "1610612748" to "Heat",
    "1610612749" to "Bucks",
    "1610612750" to "Timberwolves",
    "1610612740" to "Pelicans",
    "1610612752" to "Knicks",
    "1610612760" to "Thunder",
    "1610612753" to "Magic",
    "1610612755" to "76ers",
    "1610612756" to "Suns",
    "1610612757" to "Trail Blazers",
    "1610612758" to "Kings",
    "1610612759" to "Spurs",
    "1610612761" to "Raptors",
    "1610612762" to "Jazz",
    "1610612764" to "Wizards"
)
@Parcelize
data class TeamNameWithId(val id: String, val name: String, val conf: String, val ref: String):
    Parcelable

val teamList = listOf<TeamNameWithId>(
    TeamNameWithId("583ecb8f-fb46-11e1-82cb-f4ce4684ea4c", "Атланта Хоукс", "east", "atl"),
    TeamNameWithId("583eccfa-fb46-11e1-82cb-f4ce4684ea4c", "Бостон Селтикс", "east", "bos"),
    TeamNameWithId("583ec9d6-fb46-11e1-82cb-f4ce4684ea4c", "Бруклин Нетс", "east", "bkn"),
    TeamNameWithId("583ec8d4-fb46-11e1-82cb-f4ce4684ea4c", "Вашингтон Визардз", "east", "was"),
    TeamNameWithId("583ec928-fb46-11e1-82cb-f4ce4684ea4c", "Детройт Пистонс", "east", "det"),
    TeamNameWithId("583ec7cd-fb46-11e1-82cb-f4ce4684ea4c", "Индиана Пэйсерс", "east", "ind"),
    TeamNameWithId("583ec773-fb46-11e1-82cb-f4ce4684ea4c", "Кливленд Кавальерс", "east", "cle"),
    TeamNameWithId("583ecea6-fb46-11e1-82cb-f4ce4684ea4c", "Майами Хит", "east", "mia"),
    TeamNameWithId("583ecefd-fb46-11e1-82cb-f4ce4684ea4c", "Милуоки Бакс", "east", "mil"),
    TeamNameWithId("583ec70e-fb46-11e1-82cb-f4ce4684ea4c", "Нью-Йорк Никс", "east", "nyk"),
    TeamNameWithId("583ed157-fb46-11e1-82cb-f4ce4684ea4c", "Орландо Мэджик", "east", "orl"),
    TeamNameWithId("583ecda6-fb46-11e1-82cb-f4ce4684ea4c", "Торонто Рапторс", "east", "tor"),
    TeamNameWithId("583ec87d-fb46-11e1-82cb-f4ce4684ea4c", "Филадельфия  76ерс", "east", "phi"),
    TeamNameWithId("583ec5fd-fb46-11e1-82cb-f4ce4684ea4c", "Чикаго Буллз", "east", "chi"),
    TeamNameWithId("583ec97e-fb46-11e1-82cb-f4ce4684ea4c", "Шарлотт Хорнетс", "east", "cha"),
    TeamNameWithId("583ec825-fb46-11e1-82cb-f4ce4684ea4c", "Голден Стэйт Уорриорз", "west", "gsw"),
    TeamNameWithId("583ecf50-fb46-11e1-82cb-f4ce4684ea4c", "Даллас Маверикс", "west", "dal"),
    TeamNameWithId("583ed102-fb46-11e1-82cb-f4ce4684ea4c", "Денвер Наггетс", "west", "den"),
    TeamNameWithId("583ecdfb-fb46-11e1-82cb-f4ce4684ea4c", "Лос-Анджелес Клипперс", "west", "lac"),
    TeamNameWithId("583ecae2-fb46-11e1-82cb-f4ce4684ea4c", "Лос-Анджелес Лейкерс", "west", "lal"),
    TeamNameWithId("583eca88-fb46-11e1-82cb-f4ce4684ea4c", "Мемфис Гриззлис", "west", "mem"),
    TeamNameWithId("583eca2f-fb46-11e1-82cb-f4ce4684ea4c", "Миннесота Тимбервулвс", "west", "min"),
    TeamNameWithId("583ecc9a-fb46-11e1-82cb-f4ce4684ea4c", "Нью-Орлеан Пеликанс", "west", "nop"),
    TeamNameWithId("583ecfff-fb46-11e1-82cb-f4ce4684ea4c", "Оклахома Сити Тандер", "west", "okc"),
    TeamNameWithId("583ed056-fb46-11e1-82cb-f4ce4684ea4c", "Портленд Трэйл Блэйзерс", "west", "por"),
    TeamNameWithId("583ed0ac-fb46-11e1-82cb-f4ce4684ea4c", "Сакраменто Кингз", "west", "sac"),
    TeamNameWithId("583ecd4f-fb46-11e1-82cb-f4ce4684ea4c", "Сан-Антонио Спёрс", "west", "sas"),
    TeamNameWithId("583ecfa8-fb46-11e1-82cb-f4ce4684ea4c", "Финикс Санс", "west", "phx"),
    TeamNameWithId("583ecb3a-fb46-11e1-82cb-f4ce4684ea4c", "Хьюстон Рокетс", "west", "hou"),
    TeamNameWithId("583ece50-fb46-11e1-82cb-f4ce4684ea4c", "Юта Джаз", "west", "uta")
)