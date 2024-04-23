package com.example.mycommish.feature.onboarding.presentation.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.ui.theme.MyCommishTheme

@Composable
fun PrimaryContainerButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(dimensionResource(R.dimen.medium_padding))
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        shape = shape
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionButtonsPreview() {
    MyCommishTheme {
        PrimaryContainerButton(
            text = "Go",
            onClick = {}
        )
    }
}