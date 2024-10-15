package com.example.mycommish.feature.prize.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommish.feature.prize.domain.usecase.PrizeUseCases
import com.example.mycommish.feature.prize.domain.util.SortTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PrizeDetailsViewModel @Inject constructor(
    private val prizeUseCases: PrizeUseCases
) : ViewModel() {

    private val _searchTextState = MutableStateFlow("")
    val searchTextState = _searchTextState.asStateFlow()

    private val _sortingTypeState = MutableStateFlow(SortTypes.ByName.option)
    val sortingTypeState = _sortingTypeState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val prizeDetailsUiState =
        combine(sortingTypeState, searchTextState) { sortingType, searchText ->
            Pair(sortingType, searchText)
        }.flatMapLatest { (sortingType, searchText) ->
            prizeUseCases.getPrizes(sortingType)
                .map { prizeList ->
                    val filteredPrize = withContext(Dispatchers.Default) {
                        if (searchText.isNotBlank()) {
                            prizeList.filter { prize ->
                                prize.name.contains(searchText, ignoreCase = true) ||
                                        prize.value.contains(searchText, ignoreCase = true)
                            }.toImmutableList()
                        } else {
                            prizeList.toImmutableList()
                        }
                    }

                    PrizeDetailsUiState(
                        prizeList = filteredPrize.ifEmpty { prizeList.toImmutableList() },
                        isPrizeFound = filteredPrize.isNotEmpty(),
                        selectedSortOption = sortingType
                    )
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = PrizeDetailsUiState()
        )

    fun deletePrize(id: Long) {
        viewModelScope.launch {
            prizeUseCases.deletePrize(prizeId = id)
        }
    }

    fun sortBy(sortingType: SortTypes) {
        _sortingTypeState.value = sortingType.option
    }

    fun onSearch(searchText: String) {
        _searchTextState.value = searchText
    }

    fun clearSearchText() {
        _searchTextState.value = ""
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}