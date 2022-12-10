package io.github.cbinarycastle.kurly.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.kurly.domain.LoadSectionsUseCase
import io.github.cbinarycastle.kurly.domain.model.Page
import io.github.cbinarycastle.kurly.domain.model.Result
import io.github.cbinarycastle.kurly.domain.model.Section
import io.github.cbinarycastle.kurly.domain.model.data
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val INITIAL_PAGE = 1

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(loadSectionsUseCase: LoadSectionsUseCase) : ViewModel() {

    private val _loadEvent = MutableSharedFlow<Int>()
    private val loadEvent = flow {
        emit(INITIAL_PAGE)
        emitAll(_loadEvent)
    }

    private val sectionPageResult: StateFlow<Result<Page<Section>>> = loadEvent
        .flatMapLatest { page -> loadSectionsUseCase(page) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Result.Loading
        )

    private val sectionPage: StateFlow<Page<Section>?> = sectionPageResult
        .map { it.data }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

    private val _sections = MutableStateFlow<List<Section>>(emptyList())
    val sections = _sections.asStateFlow()

    val isLoading: StateFlow<Boolean> = sectionPageResult
        .map { it == Result.Loading }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    private val sectionList = mutableListOf<Section>()

    init {
        viewModelScope.launch {
            sectionPage
                .filterNotNull()
                .collect {
                    addSections(it.data)
                }
        }
    }

    fun loadMore() {
        sectionPage.value?.nextPage?.let {
            load(it)
        }
    }

    private fun load(page: Int) {
        viewModelScope.launch {
            _loadEvent.emit(page)
        }
    }

    private suspend fun addSections(sections: List<Section>) {
        sectionList.addAll(sections)
        _sections.emit(sectionList)
    }
}

