package com.example.drutask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drutask.R
import com.example.drutask.data.model.dataModel.Movie

import com.example.drutask.databinding.MovieComponentBinding
import com.example.drutask.utils.Constants
import com.example.drutask.base.OnClickListener

class MoviesAdapter(private val onClickListener: OnClickListener<Movie>): ListAdapter<Movie, MoviesAdapter.ViewHolder>(MoviesAdapter) {

    companion object AlbumCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: MovieComponentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, view: View) {
            binding.movieName.text = movie.name
            Glide.with(view.context)
                .load(Constants.createImageUrl(movie.imageUrl))
                .placeholder(R.drawable.ic_loading_image_icon)
                .into(binding.movieImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        return ViewHolder(
            MovieComponentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, holder.itemView)
        holder.itemView.setOnClickListener {
            onClickListener.clickListener(movie)
        }
    }
}