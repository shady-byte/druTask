package com.example.drutask.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drutask.data.model.dataModel.Movie
import com.example.drutask.domain.model.CategoryData
import com.example.drutask.domain.model.MovieDataWrapper
import com.example.drutask.domain.usecase.GetMoviesUseCase
import com.example.drutask.utils.CategoriesTypeEnum
import com.example.drutask.utils.Constants
import com.example.drutask.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {
    val categoriesList = MutableLiveData(Constants.categoriesList)

    private val categoriesPageNumbers = mutableListOf(0, 0, 0, 0)

    fun getMovies(categoryType: CategoriesTypeEnum) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = getMoviesUseCase(createMoviesDateWrapper(categoryType))) {
                is ResponseStatus.OnError -> {
                    Log.d("MainTest", response.exception.message.toString())
                }

                is ResponseStatus.OnSuccess -> {
                    updateCategoryWithNewMovies(categoryType, response.data)
                    Log.d("MainTest", response.data.toString())
                }

                is ResponseStatus.IsLoading -> {}
            }
        }
    }

    private fun updateCategoryWithNewMovies(categoryType: CategoriesTypeEnum, movies: List<Movie>) {
        val currentList = categoriesList.value?.toMutableList()
        val newMovies = currentList?.find { it.category == categoryType }?.movies?.plus(movies)
        currentList?.find { it.category == categoryType }?.movies = newMovies ?: emptyList()
        categoriesList.postValue(currentList ?: emptyList())
    }

    private fun createMoviesDateWrapper(categoryType: CategoriesTypeEnum): MovieDataWrapper {
        return MovieDataWrapper(
            pageNumber = when (categoryType) {
                CategoriesTypeEnum.POPULAR -> {
                    categoriesPageNumbers[0]++
                    categoriesPageNumbers.first()
                }

                CategoriesTypeEnum.TOP_RATED -> {
                    categoriesPageNumbers[1]++
                    categoriesPageNumbers[1]
                }

                CategoriesTypeEnum.NOW_PLAYING -> {
                    categoriesPageNumbers[2]++
                    categoriesPageNumbers[2]
                }

                CategoriesTypeEnum.UPCOMING -> {
                    categoriesPageNumbers[3]++
                    categoriesPageNumbers.last()
                }
            },
            categoryType = categoryType.apiName
        )
    }
}