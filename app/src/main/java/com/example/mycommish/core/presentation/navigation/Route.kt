package com.example.mycommish.core.presentation.navigation

import com.example.mycommish.R

sealed class Route(
    val route: String,
    val activeIcon: Int = 0,
    val inactiveIcon: Int = 0
) {
    data object Home : Route(route = "home") {
        data object Dashboard : Route(
            route = "dashboard",
            activeIcon = R.drawable.dashboard_active_icon,
            inactiveIcon = R.drawable.dashboard_inactive_icon
        )

        data object Prize : Route(
            route = "prize",
            activeIcon = R.drawable.prize_active_icon,
            inactiveIcon = R.drawable.prize_inactive_icon
        )

        data object TrackEarnings : Route(
            route = "track_earnings",
            activeIcon = R.drawable.track_earnings_active_icon,
            inactiveIcon = R.drawable.track_earnings_inactive_icon
        )
    }

    data object AppStart : Route(route = "app_start") {
        data object OnBoarding : Route(
            route = "onboarding"
        )
    }

    companion object {
        val navigationItems = listOf(
            Home.Dashboard, Home.Prize, Home.TrackEarnings
        )
    }

}
