package com.example.drutask.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.drutask.base.OnClickListener
import com.example.drutask.data.model.dataModel.Movie
import com.example.drutask.databinding.FragmentMoviesListBinding
import com.example.drutask.ui.adapter.CategoriesAdapter
import com.example.drutask.ui.viewModel.MoviesListViewModel

class MoviesListFragment : Fragment() {
    private lateinit var binding: FragmentMoviesListBinding
    private val viewModel by activityViewModels<MoviesListViewModel>()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.supportActionBar?.show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        initAdapter()
        setRecyclerView()
    }

    private fun setUpObservers() {
        with(viewModel) {
            categoriesList.observe(viewLifecycleOwner) {
                categoriesAdapter.submitList(it)
            }
        }
    }

    private fun initAdapter() {
        if (this::categoriesAdapter.isInitialized) return

        categoriesAdapter = CategoriesAdapter(OnClickListener { movie ->
            navigateToMovieDetails(movie)
        }) { category ->
            viewModel.getMovies(category)
        }
    }

    private fun setRecyclerView() {
        binding.categoriesRecyclerView.apply {
            adapter = categoriesAdapter
        }
    }

    private fun navigateToMovieDetails(movie: Movie) {
        findNavController().navigate(
            MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(
                movie
            )
        )
    }
}