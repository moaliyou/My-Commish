package com.example.mycommish.feature.onboarding.data.local

import com.example.mycommish.R
import com.example.mycommish.feature.onboarding.domain.model.Page

object PagesLocalDataSource {
    val pages = listOf(
        Page(
            title = R.string.page_one_title,
            subtitle = R.string.page_one_subtitle,
            image = R.drawable.screen_1_light
        ),
        Page(
            title = R.string.page_two_title,
            subtitle = R.string.page_two_subtitle,
            image = R.drawable.screen_2_light
        ),
        Page(
            title = R.string.page_three_title,
            subtitle = R.string.page_three_subtitle,
            image = R.drawable.screen_3_light
        )
    )
}