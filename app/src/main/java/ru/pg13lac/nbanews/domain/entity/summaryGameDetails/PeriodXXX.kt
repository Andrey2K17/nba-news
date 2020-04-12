package ru.pg13lac.nbanews.domain.entity.summaryGameDetails

data class PeriodXXX(
    val assists: Int,
    val assists_turnover_ratio: Double,
    val bench_points: Int,
    val biggest_lead: Int,
    val blocked_att: Int,
    val blocks: Int,
    val coach_tech_fouls: Int,
    val defensive_points_per_possession: Double,
    val defensive_rating: Double,
    val defensive_rebounds: Int,
    val effective_fg_pct: Double,
    val efficiency: Int,
    val efficiency_game_score: Double,
    val fast_break_att: Int,
    val fast_break_made: Int,
    val fast_break_pct: Double,
    val fast_break_pts: Int,
    val field_goals_att: Int,
    val field_goals_made: Int,
    val field_goals_pct: Double,
    val flagrant_fouls: Int,
    val fouls_drawn: Int,
    val free_throws_att: Int,
    val free_throws_made: Int,
    val free_throws_pct: Double,
    val id: String,
    val minutes: String,
    val number: Int,
    val offensive_fouls: Int,
    val offensive_points_per_possession: Double,
    val offensive_rating: Double,
    val offensive_rebounds: Int,
    val opponent_possessions: Double,
    val personal_fouls: Int,
    val player_tech_fouls: Int,
    val pls_min: Int,
    val points: Int,
    val points_against: Int,
    val points_in_paint: Int,
    val points_in_paint_att: Int,
    val points_in_paint_made: Int,
    val points_in_paint_pct: Double,
    val points_off_turnovers: Int,
    val possessions: Double,
    val rebounds: Int,
    val second_chance_att: Int,
    val second_chance_made: Int,
    val second_chance_pct: Double,
    val second_chance_pts: Int,
    val sequence: Int,
    val steals: Int,
    val team_defensive_rebounds: Int,
    val team_fouls: Int,
    val team_offensive_rebounds: Int,
    val team_rebounds: Int,
    val team_tech_fouls: Int,
    val team_turnovers: Int,
    val three_points_att: Int,
    val three_points_made: Int,
    val three_points_pct: Double,
    val time_leading: String,
    val total_fouls: Int,
    val total_rebounds: Int,
    val true_shooting_att: Double,
    val true_shooting_pct: Double,
    val turnovers: Int,
    val two_points_att: Int,
    val two_points_made: Int,
    val two_points_pct: Double,
    val type: String
)