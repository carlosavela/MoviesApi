package com.ing.cavl.moviesapi.ui.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ing.cavl.moviesapi.databinding.MovieUpcomingRowBinding
import com.ing.cavl.moviesapi.ui.core.BaseConcatHolder

class UpcomingConcatAdapter(
    private val moviesAdapter: MovieAdapter,
    val myInterface: onSeeMoreMovies
) : RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {

        val itemBinding =
            MovieUpcomingRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: MovieUpcomingRowBinding) :
        BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvUpcomingMovies.adapter = adapter
            binding.btnSeeMore.setOnClickListener {
                myInterface.onClickMoreMovis()
            }
        }
    }

    interface onSeeMoreMovies {
        fun onClickMoreMovis()
    }

}