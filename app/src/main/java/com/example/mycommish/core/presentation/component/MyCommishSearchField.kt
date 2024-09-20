package com.example.mycommish.core.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme

@Composable
fun MyCommishSearchField(
    modifier: Modifier = Modifier,
    inputValue: String,
    onInputValueChange: (String) -> Unit,
    placeholder: String = "",
    trailingIcon: @Composable() (() -> Unit)? = null
) {
    TextField(
        value = inputValue,
        onValueChange = onInputValueChange,
        singleLine = true,
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = "Search icon",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(FilterChipDefaults.IconSize)
            )
        },
        trailingIcon = trailingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.onErrorContainer,
            errorSupportingTextColor = MaterialTheme.colorScheme.error
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.extra_medium_padding)),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun MyCommishSearchFieldPreview() {
    var searchText by remember{ mutableStateOf("") }

    MyCommishTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.extra_medium_padding))
        ) {
            MyCommishSearchField(
                modifier = Modifier.fillMaxWidth(),
                inputValue = searchText,
                onInputValueChange = { searchText = it },
                placeholder = "Search by name or value",
                trailingIcon = {
                    if (searchText.isNotEmpty() && searchText.isNotBlank()) {
                        IconButton(onClick = { searchText = "" }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_clear),
                                contentDescription = "Clear icon",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    }
                }
            )
        }
    }
}