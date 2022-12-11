package io.github.cbinarycastle.kurly.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.cbinarycastle.kurly.domain.LoadSectionsUseCase
import io.github.cbinarycastle.kurly.domain.model.Result
import io.github.cbinarycastle.kurly.domain.model.Section
import io.github.cbinarycastle.kurly.ui.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(loadSectionsUseCase: LoadSectionsUseCase) : ViewModel() {

    private var nextPage: Int? = null

    private val _loadEvent = MutableSharedFlow<LoadEvent>()
    private val loadEvent = flow {
        emit(LoadEvent.Refresh)
        emitAll(_loadEvent)
    }

    private val latestLoadEvent = loadEvent.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    private val loadEventAndResult = loadEvent
        .flatMapLatest { event ->
            loadSectionsUseCase(event.page).map { result -> event to result }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

    val sections: StateFlow<LoadResult<List<Section>>> = loadEventAndResult
        .filterNotNull()
        .onEach { (_, result) ->
            if (result is Result.Success) {
                val page = result.data
                sectionList.addAll(page.data)
                nextPage = page.nextPage
            }
        }
        .map { (event, result) ->
            LoadResult(
                data = sectionList.toList(),
                loadStates = LoadStates(
                    refresh = if (event == LoadEvent.Refresh) result.loadState else LoadState.NOT_LOADING,
                    append = if (event is LoadEvent.Append) result.loadState else LoadState.NOT_LOADING
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = LoadResult(data = emptyList(), loadStates = LoadStates.IDLE)
        )

    private val sectionList = mutableListOf<Section>()

    fun refresh() {
        sectionList.clear()
        load(LoadEvent.Refresh)
    }

    fun loadMore() {
        val loadStates = sections.value.loadStates

        if (loadStates.refresh == LoadState.NOT_LOADING &&
            loadStates.append == LoadState.NOT_LOADING
        ) {
            nextPage?.let {
                load(LoadEvent.Append(it))
            }
        }
    }

    fun retry() {
        latestLoadEvent.value?.let {
            load(it)
        }
    }

    private fun load(event: LoadEvent) {
        viewModelScope.launch {
            _loadEvent.emit(event)
        }
    }
}

