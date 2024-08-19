package com.example.mycommish.feature.prize.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.component.MyCommishFilledTextField
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme
import com.example.mycommish.core.util.AppConstants
import com.example.mycommish.feature.prize.domain.model.Prize

@Composable
fun PrizeInputForm(
    modifier: Modifier = Modifier,
    prize: Prize,
    validatorHasErrors: Boolean,
    errorMessage: String,
    onValueChange: (Prize) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues()
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.extra_medium_padding))
    ) {
        MyCommishFilledTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = prize.name,
            onValueChange = { newPrizeName -> onValueChange(prize.copy(name = newPrizeName)) },
            placeholder = { Text(text = stringResource(R.string.prize_name_label)) },
            keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
            isError = validatorHasErrors,
            supportingText = {
                if (validatorHasErrors) {
                    Text(text = errorMessage)
                }
            }
        )
        MyCommishFilledTextField(
            modifier = Modifier
                .fillMaxWidth(),
            text = prize.value,
            onValueChange = { newPrizeValue -> onValueChange(prize.copy(value = newPrizeValue)) },
            placeholder = { Text(text = stringResource(R.string.prize_value_label)) },
            keyboardOption = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            isError = validatorHasErrors,
            supportingText = {
                if (validatorHasErrors) {
                    Text(text = errorMessage)
                }
            }
        )
        MyCommishFilledTextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            text = prize.description,
            onValueChange = { newPrizeDescription ->
                onValueChange(prize.copy(description = newPrizeDescription))
            },
            placeholder = {
                Text(text = stringResource(R.string.prize_description_label))
            },
            singleLine = false
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PrizeInputFormPreview() {
    MyCommishTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.medium_padding))
        ) {
            PrizeInputForm(
                prize = Prize(),
                validatorHasErrors = true,
                errorMessage = AppConstants.PRIZE_NAME_TOO_SHORT_ERROR
            )
        }
    }
}