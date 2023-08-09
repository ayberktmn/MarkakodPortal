package com.example.markakodportal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.markakodportal.databinding.FragmentHomeBinding
import com.example.markakodportal.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgMenu.setOnClickListener {
            navigateToBottombar(R.id.home)
        }

        binding.bottomNavigationView.setOnNavigationItemReselectedListener {}
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.home ->

                    childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.homeFragment)


                R.id.socialChat -> {
                    // Profile öğesine tıklandığında yapılacak işlemler
                    childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.socailNetworkFragment)
                }

                R.id.profile ->
                    childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.profileFragment)


                R.id.aboutus -> childFragmentManager.primaryNavigationFragment?.findNavController()
                    ?.navigate(R.id.aboutusFragment)

                else -> {

                }
            }
            true
        }

    }
    private fun navigateToBottombar(id: Int) {   // Bottom bar ın seçilen id sine göre yönlendiriyor
        val bottomNav =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = id
    }
}