package com.example.drutask.ui.view

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.drutask.R
import com.example.drutask.databinding.FragmentSplashBinding
import com.example.drutask.ui.viewModel.MoviesListViewModel
import com.example.drutask.utils.CategoriesTypeEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel by activityViewModels<MoviesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.supportActionBar?.hide()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                navigateToHomeScreen()
            }
        }.start()


        viewModel.getMovies(CategoriesTypeEnum.POPULAR)
        viewModel.getMovies(CategoriesTypeEnum.TOP_RATED)
        viewModel.getMovies(CategoriesTypeEnum.NOW_PLAYING)
        viewModel.getMovies(CategoriesTypeEnum.UPCOMING)

    }

    private fun navigateToHomeScreen() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMoviesListFragment())
    }
}