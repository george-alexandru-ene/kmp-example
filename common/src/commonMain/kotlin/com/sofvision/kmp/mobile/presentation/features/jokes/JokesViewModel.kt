package com.sofvision.kmp.mobile.presentation.features.jokes

import com.sofvision.kmp.mobile.di.ServiceLocator
import com.sofvision.kmp.mobile.domain.entities.Joke
import com.sofvision.kmp.mobile.domain.usecases.GetJokes
import com.sofvision.kmp.mobile.presentation.models.UiState
import com.sofvision.kmp.mobile.presentation.base.BaseViewModel
import com.sofvision.kmp.mobile.presentation.helpers.asCommonFlow
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.launch
import kotlin.native.concurrent.ThreadLocal

@ExperimentalCoroutinesApi
class JokesViewModel(private val getJokes: GetJokes) : BaseViewModel() {
    private val _jokesState = ConflatedBroadcastChannel<UiState>()
    val jokesState = _jokesState.asCommonFlow()

    private val _jokes = ConflatedBroadcastChannel<List<Joke>>()
    val jokes = _jokes.asCommonFlow()

    init {
        clientScope.launch(CoroutineExceptionHandler { _, throwable ->
            _jokesState.offer(UiState.Error(throwable))
        }) {
            _jokesState.offer(UiState.Loading)
            _jokes.offer(getJokes())
            _jokesState.offer(UiState.Success)
        }
    }

    @ThreadLocal
    companion object {
        fun create() = JokesViewModel(ServiceLocator.getJokes)
    }
}