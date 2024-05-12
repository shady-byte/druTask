package com.example.drutask.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.drutask.R
import com.example.drutask.data.dataSources.remoteDataSource.ApiUrls
import com.example.drutask.data.model.dataModel.Movie
import com.example.drutask.databinding.FragmentMovieDetailsBinding
import com.example.drutask.databinding.FragmentMoviesListBinding

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val movieSelected = MovieDetailsFragmentArgs.fromBundle(requireArguments()).selectedMovie
        (activity as MainActivity).supportActionBar?.title = movieSelected.name
        setMovieDetails(movieSelected)
        return binding.root
    }

    private fun setMovieDetails(movieSelected: Movie) {
        binding.movieName.text = movieSelected.name

        val split = movieSelected.productionDate.split("-")
        binding.movieDateRating.text =
            context?.getString(R.string.movie_date_rating_format, split[0], movieSelected.rating)

        binding.movieDescription.text = movieSelected.description

        Glide.with(this)
            .load(createImageUrl(movieSelected.imageUrl))
            .placeholder(R.drawable.ic_loading_image_icon)
            .into(
                binding.movieImage
            )
    }

    private fun createImageUrl(splitUrl: String): String {
        return ApiUrls.MOVIE_IMAGE_BASE_URL + splitUrl
    }
}