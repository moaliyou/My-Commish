package com.example.mycommish.feature.onboarding.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.core.presentation.ui.theme.primaryContainerBackground
import com.example.mycommish.core.presentation.ui.theme.primaryContainerContent

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
            containerColor = primaryContainerContent,
            contentColor = primaryContainerBackground
        ),
        shape = shape
    ) {
        Text(text = text)
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    contentColor: Color = Color.Unspecified,
    onClick: () -> Unit
){
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            color = contentColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionButtonsPreview() {
    MyCommishTheme {
        Column {
            PrimaryContainerButton(
                text = "Go",
                onClick = {}
            )
            SecondaryButton(
                text = "Back",
                onClick = {}
            )
        }
    }
}