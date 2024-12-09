package com.example.jokeapp.presentation.screens.savedJokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.databinding.ItemLayoutBinding
import com.example.jokeapp.domain.model.Jokes

class SavedJokesAdapter :
    ListAdapter<Jokes, SavedJokesAdapter.SavedJokesViewHolder>(JokeDiffCallBack()) {

    var onButtonDeleteClick: (joke: Jokes) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedJokesViewHolder {
        return SavedJokesViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SavedJokesViewHolder, position: Int) {
        holder.bind(joke = getItem(position))
    }

    class JokeDiffCallBack() : DiffUtil.ItemCallback<Jokes>() {
        override fun areItemsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
            return oldItem == newItem
        }
    }

    inner class SavedJokesViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Jokes) = with(binding) {
            setup.text = joke.setup
            punchline.text = joke.punchline

            deleteButton.setOnClickListener {
                onButtonDeleteClick.invoke(joke)
            }
        }
    }
}