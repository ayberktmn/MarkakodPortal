package com.example.markakodportal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import com.example.markakodportal.databinding.FragmentAboutusBinding


class AboutusFragment : Fragment() {

    private var _binding:FragmentAboutusBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutusBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }

        val cikis = requireActivity().findViewById<ImageView>(R.id.imgback)
        cikis.visibility = View.GONE
        return view
    }
}