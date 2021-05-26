package com.ing.cavl.moviesapi.ui.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ing.cavl.moviesapi.databinding.NowPlayingMoviesRowBinding
import com.ing.cavl.moviesapi.databinding.PopularMoviesRowBinding
import com.ing.cavl.moviesapi.ui.core.BaseConcatHolder

class NowConcatAdapter (private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {

        val itemBinding =
            NowPlayingMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: NowPlayingMoviesRowBinding) : BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvNowPlayingMovies.adapter = adapter
        }
    }

}