package com.example.jokeapp.presentation.screens.jokeGenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jokeapp.databinding.FragmentJokeGenratorBinding
import kotlinx.coroutines.launch

class JokeGeneratorFragment : Fragment() {

    private lateinit var binding: FragmentJokeGenratorBinding
    private val viewModel by viewModels<JokesGeneratorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJokeGenratorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCollectors()
        setListeners()

    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.jokesFlow.collect {
                binding.setUpTextview.text = it?.setup
                binding.punchlineTextview.text = it?.punchline
            }
        }
    }

    private fun setListeners() = with(binding) {
        buttonGenerateJoke.setOnClickListener {
            viewModel.getRandomJoke()
        }

        buttonSaveJoke.setOnClickListener {
            viewModel.saveRandomJoke()
            viewModel.getRandomJoke()
        }

        favourites.setOnClickListener {
            findNavController().navigate(
                JokeGeneratorFragmentDirections.actionJokeGeneratorFragmentToSavedJokesFragment()
            )
        }
    }

}