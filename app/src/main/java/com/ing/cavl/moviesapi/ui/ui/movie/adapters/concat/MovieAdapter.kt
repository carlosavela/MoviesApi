package com.ing.cavl.moviesapi.ui.ui.movie.adapters.concat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ing.cavl.moviesapi.databinding.MovieItemBinding
import com.ing.cavl.moviesapi.ui.aplication.AppConstants
import com.ing.cavl.moviesapi.ui.core.BaseViewHolder
import com.ing.cavl.moviesapi.ui.data.model.Movie

class MovieAdapter(
    private val moviesList: List<Movie>,
    private val itemClickListener: onMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface onMovieClickListener {
        fun onMovieClick(movie: Movie)
        fun onTitleClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)


        itemBinding.imgMovie.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener

            itemClickListener.onMovieClick(moviesList[position])

        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is MoviesViewHolder -> holder.bind(moviesList[position])
        }
    }

    override fun getItemCount(): Int = moviesList.size


    // forma parte de MovieAdapter como un hijo
    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context) :
        BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {

            Glide.with(context).load(AppConstants.BASE_URL_IMAGE + item.poster_path)
                .centerCrop().into(binding.imgMovie)

            binding.txvTitleItem.text = item.title

            binding.txvTitleItem.setOnClickListener {
                itemClickListener.onTitleClick(item)
            }

        }
    }
}