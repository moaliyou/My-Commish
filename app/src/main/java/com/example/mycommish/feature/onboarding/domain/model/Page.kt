package com.example.mycommish.feature.onboarding.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Page(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    @DrawableRes val image: Int = 0,
)
