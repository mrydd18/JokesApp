package com.example.jokeapp.presentation.screens.savedJokes

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.jokeapp.databinding.FragmentSavedJokesBinding
import com.example.jokeapp.domain.model.Jokes
import kotlinx.coroutines.launch

class SavedJokesFragment : Fragment() {
    private lateinit var binding: FragmentSavedJokesBinding
    private val viewModel by viewModels<SavedJokesViewModel>()
    private val savedJokesAdapter = SavedJokesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllSavedJokes()

        initRecyclerView()
        setCollectors()
        setListeners()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = savedJokesAdapter
        }
    }

    private fun setCollectors() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.savedJokesFlow.collect { listOfJokes ->
            savedJokesAdapter.submitList(listOfJokes)
            if (listOfJokes.isEmpty()) {
                binding.noSavedJokes.visibility = View.VISIBLE
            } else {
                binding.noSavedJokes.visibility = View.GONE
            }
        }
    }

    private fun setListeners() {
        savedJokesAdapter.onButtonDeleteClick = { currentJoke ->
            showDeleteConfirmationDialog(currentJoke)
        }
    }

    private fun showDeleteConfirmationDialog(joke: Jokes) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Joke")
            .setMessage("Are you sure you want to delete this joke?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.deleteJoke(joke)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}