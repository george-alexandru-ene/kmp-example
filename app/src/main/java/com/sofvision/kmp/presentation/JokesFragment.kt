package com.sofvision.kmp.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.sofvision.kmp.R
import com.sofvision.kmp.mobile.presentation.features.jokes.JokesViewModel
import com.sofvision.kmp.mobile.presentation.features.jokes.JokesViewModelFactory
import com.sofvision.kmp.mobile.presentation.models.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class JokesFragment : Fragment(R.layout.fragment_jokes) {
    private val viewModel: JokesViewModel by viewModels { JokesViewModelFactory() }
    private val jokesAdapter by lazy { JokesListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recJokes = view.findViewById<RecyclerView>(R.id.rec_jokes)

        val loadingJokes = view.findViewById<ProgressBar>(R.id.loading_jokes)
        val emptyJokes = view.findViewById<TextView>(R.id.empty_jokes)

        recJokes.adapter = jokesAdapter

        viewModel.jokesState.asLiveData().observe(viewLifecycleOwner) {
            recJokes.visibility = if (it is UiState.Success) View.VISIBLE else View.INVISIBLE
            loadingJokes.visibility = if (it is UiState.Loading) View.VISIBLE else View.GONE
            emptyJokes.visibility = if (it is UiState.Error) View.VISIBLE else View.GONE
        }

        viewModel.jokes.asLiveData().observe(viewLifecycleOwner) {
            jokesAdapter.submitList(it)
        }
    }
}