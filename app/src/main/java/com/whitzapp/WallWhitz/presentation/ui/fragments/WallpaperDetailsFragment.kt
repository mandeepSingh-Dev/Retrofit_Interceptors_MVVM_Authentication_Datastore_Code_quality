package com.whitzapp.WallWhitz.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whitzapp.WallWhitz.databinding.FragmentWallpaperDetailsBinding


class WallpaperDetailsFragment : Fragment() {

    var _binding: FragmentWallpaperDetailsBinding? = null
    val binding : FragmentWallpaperDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWallpaperDetailsBinding.inflate(layoutInflater)
        return binding.root
    }
}