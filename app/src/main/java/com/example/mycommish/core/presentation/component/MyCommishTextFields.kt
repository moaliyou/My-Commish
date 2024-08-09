package com.example.mycommish.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme

@Composable
fun MyCommishFilledTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    placeholder: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOption: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        singleLine = singleLine,
        placeholder = placeholder,
        isError = isError,
        supportingText = supportingText,
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
        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_padding)),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOption,
        keyboardActions = keyboardActions
    )
}

@Preview(showBackground = true)
@Composable
private fun MyCommishFilledTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    var validatorHasError by remember { mutableStateOf(false) }

    MyCommishTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.extra_medium_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_medium_padding))
        ) {
            MyCommishFilledTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = text,
                onValueChange = {
                    text = it
                    validatorHasError = it.length < 3 && it.isNotEmpty()
                },
                placeholder = { Text(text = stringResource(R.string.prize_name_label)) },
                isError = validatorHasError,
                supportingText = {
                    if (validatorHasError) {
                        Text(text = "Prize name must be at least 3 characters")
                    }
                }
            )
            MyCommishFilledTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = text,
                onValueChange = { text = it },
                placeholder = { Text(text = stringResource(R.string.prize_value_label)) }
            )
            MyCommishFilledTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                text = text,
                onValueChange = { text = it },
                placeholder = {
                    Text(text = stringResource(R.string.prize_description_label))
                },
                singleLine = false
            )
        }
    }
}