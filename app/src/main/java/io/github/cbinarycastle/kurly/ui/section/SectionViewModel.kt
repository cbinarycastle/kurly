package io.github.cbinarycastle.kurly.ui.section

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.kurly.domain.LoadProductsUseCase
import io.github.cbinarycastle.kurly.domain.model.Result
import io.github.cbinarycastle.kurly.domain.model.data
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SectionViewModel @Inject constructor(
    loadProductsUseCase: LoadProductsUseCase,
) : ViewModel() {

    private val section = MutableStateFlow<SectionModel?>(null)

    private val productsResult = section
        .filterNotNull()
        .flatMapLatest { loadProductsUseCase(it.id) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Result.Loading
        )

    val products = productsResult
        .map { it.data ?: emptyList() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val isLoading = productsResult
        .map { it == Result.Loading }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    fun setSection(section: SectionModel) {
        this.section.value = section
    }
}