package com.example.drutask.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(private val onLoadMore: () -> Unit) : RecyclerView.OnScrollListener() {
    companion object {
        private const val VISIBLE_THRESHOLD = 2
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager

        val isHorizontal = layoutManager.orientation == LinearLayoutManager.HORIZONTAL
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()

        val endHasBeenReached = if (isHorizontal) {
            totalItemCount <= (lastVisibleItemPosition + VISIBLE_THRESHOLD) && dx > 0
        } else {
            totalItemCount <= (lastVisibleItemPosition + VISIBLE_THRESHOLD) && dy > 0
        }

        if (endHasBeenReached) {
            onLoadMore()
        }
    }
}