package com.ing.cavl.moviesapi.ui.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ing.cavl.moviesapi.databinding.FragmentMovieDetailBinding
import com.ing.cavl.moviesapi.databinding.FragmentMoviesBinding
import com.ing.cavl.moviesapi.ui.aplication.AppConstants
import com.ing.cavl.moviesapi.ui.ui.movie.MoviesFragmentDirections


class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(AppConstants.BASE_URL_IMAGE + args.posterImageUrl)
            .centerCrop().into(binding.imgMovie)

        Glide.with(requireContext()).load(AppConstants.BASE_URL_IMAGE + args.backgroundUrl)
            .centerCrop().into(binding.imgBackground)

        binding.txvTitle.text = args.title
        binding.txvDescription.text = args.overview
        binding.txvReleased.text = "Fecha ${args.releaseData}"
        binding.txvLanguage.text = "Language ${args.language}"
        binding.txvRating.text = "${args.voteAverage} ( ${args.voteCount} vistas)"

        Log.d("Language", " ${args.language}")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}