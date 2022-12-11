package io.github.cbinarycastle.kurly.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.kurly.domain.LoadSectionsUseCase
import io.github.cbinarycastle.kurly.domain.model.Result
import io.github.cbinarycastle.kurly.domain.model.Section
import io.github.cbinarycastle.kurly.ui.model.DataWithLoadState
import io.github.cbinarycastle.kurly.ui.model.LoadState
import io.github.cbinarycastle.kurly.ui.model.loadState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val INITIAL_PAGE = 1

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(loadSectionsUseCase: LoadSectionsUseCase) : ViewModel() {

    private var nextPage: Int? = INITIAL_PAGE

    private val _loadEvent = MutableSharedFlow<Int>()
    private val loadEvent = flow {
        emit(INITIAL_PAGE)
        emitAll(_loadEvent)
    }

    val sections: StateFlow<DataWithLoadState<List<Section>>> = loadEvent
        .flatMapLatest { page -> loadSectionsUseCase(page) }
        .onEach {
            if (it is Result.Success) {
                val page = it.data
                sectionList.addAll(page.data)
                nextPage = page.nextPage
            }
        }
        .map { DataWithLoadState(sectionList.toList(), it.loadState) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = DataWithLoadState(emptyList(), LoadState.NOT_LOADING)
        )

    private val sectionList = mutableListOf<Section>()

    fun loadMore() {
        if (sections.value.loadState == LoadState.NOT_LOADING) {
            nextPage?.let {
                load(it)
            }
        }
    }

    private fun load(page: Int) {
        viewModelScope.launch {
            _loadEvent.emit(page)
        }
    }
}

