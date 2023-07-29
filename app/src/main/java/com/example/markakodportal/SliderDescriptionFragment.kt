package com.example.markakodportal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.markakodportal.databinding.FragmentSliderDescriptionBinding


class SliderDescriptionFragment : Fragment() {

    private var _binding : FragmentSliderDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSliderDescriptionBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.txtBack.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }
}