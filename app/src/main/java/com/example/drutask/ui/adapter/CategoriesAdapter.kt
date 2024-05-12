package com.example.drutask.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.drutask.base.EndlessScrollListener
import com.example.drutask.data.model.dataModel.Movie
import com.example.drutask.databinding.CategoryMoviesComponentBinding
import com.example.drutask.domain.model.CategoryData
import com.example.drutask.base.OnClickListener
import com.example.drutask.utils.CategoriesTypeEnum

class CategoriesAdapter(
    private val onMovieClickListener: OnClickListener<Movie>,
    private val loadMoreMovies: ((category: CategoriesTypeEnum) -> Unit)
) : ListAdapter<CategoryData, CategoriesAdapter.ViewHolder>(AlbumCallBack) {

    companion object AlbumCallBack : DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.category == newItem.category
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.movies == newItem.movies
        }
    }

    inner class ViewHolder(private val binding: CategoryMoviesComponentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val moviesAdapter = MoviesAdapter(onMovieClickListener)

        init {
            binding.moviesRecyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.moviesRecyclerView.adapter = moviesAdapter
        }

        fun bind(categoryData: CategoryData, view: View) {
            binding.categoryName.text = categoryData.category.displayName
            binding.moviesRecyclerView.apply {
                moviesAdapter.submitList(categoryData.movies.toList())
                clearOnScrollListeners()
                addOnScrollListener(EndlessScrollListener {
                    loadMoreMovies(categoryData.category)
                })
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesAdapter.ViewHolder {
        return ViewHolder(
            CategoryMoviesComponentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        val categoryData = getItem(position)
        holder.bind(categoryData, holder.itemView)
    }
}