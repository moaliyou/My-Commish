package com.example.mycommish.feature.onboarding.data.local

import com.example.mycommish.R
import com.example.mycommish.feature.onboarding.domain.model.Page

object PagesLocalDataSource {
    val pages = listOf(
        Page(
            title = R.string.page_one_title,
            subtitle = R.string.page_one_subtitle,
            image = R.drawable.money_img_on
        ),
        Page(
            title = R.string.page_two_title,
            subtitle = R.string.page_two_subtitle,
            image = R.drawable.struggle_img_on
        ),
        Page(
            title = R.string.page_three_title,
            subtitle = R.string.page_three_subtitle,
            image = R.drawable.track_img_on
        )
    )
}