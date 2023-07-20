package com.example.markakodportal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.markakodportal.databinding.FragmentHomeBinding
import com.example.markakodportal.databinding.FragmentMainBinding


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
        binding.bottomNavigationView.setOnNavigationItemReselectedListener {}
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.home ->

                    childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.homeFragment)


                R.id.message -> {
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
}