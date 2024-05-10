package com.example.mycommish.feature.home.presentation.navigation

import com.example.mycommish.R

sealed class Screen(
    val route: String,
    val activeIcon: Int = 0,
    val inactiveIcon: Int = 0
) {
    data object Dashboard : Screen(
        route = "dashboard",
        activeIcon = R.drawable.dashboard_active_icon,
        inactiveIcon = R.drawable.dashboard_inactive_icon
    )

    data object Prize : Screen(
        route = "prize",
        activeIcon = R.drawable.prize_active_icon,
        inactiveIcon = R.drawable.prize_inactive_icon
    )

    data object TrackEarnings : Screen(
        route = "track_earnings",
        activeIcon = R.drawable.track_earnings_active_icon,
        inactiveIcon = R.drawable.track_earnings_inactive_icon
    )

    companion object {
        fun getNavigationItems(): List<Screen> = listOf(
            Dashboard, Prize, TrackEarnings
        )
    }

}
