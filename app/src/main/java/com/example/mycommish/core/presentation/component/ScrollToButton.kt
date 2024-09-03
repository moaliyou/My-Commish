package com.example.mycommish.core.presentation.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Icon(
            Icons.Filled.KeyboardArrowUp,
            contentDescription = "Floating action button.",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun ScrollToButtonPreview() {
    MyCommishTheme {
        ScrollToTopButton(onClick = {})
    }
}