package com.example.mycommish.feature.onboarding.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.mycommish.R

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPagePosition: Int,
    activePageColor: Color = MaterialTheme.colorScheme.primary,
    inactivePageColor: Color = MaterialTheme.colorScheme.surface
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageSize) { iteration ->
            val color =
                if (iteration == selectedPagePosition) activePageColor else inactivePageColor

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_padding)))
                    .background(color)
                    .size(
                        width = dimensionResource(R.dimen.extra_large_padding),
                        height = dimensionResource(R.dimen.medium_padding)
                    )
            )
        }
    }
}