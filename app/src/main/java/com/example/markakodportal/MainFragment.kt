package com.example.markakodportal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.markakodportal.databinding.FragmentHomeBinding
import com.example.markakodportal.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_open_anim) }
    private val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_close_anim) }
    private val rotateFrom : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom_anim) }
    private val rotateToBottom : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.to_bottom_anim) }
    private var cliked = false


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

        binding.fab.setOnClickListener {
            onAddButtonCliked()
        }
        binding.editFab?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_sendTextFragment)

        }
        binding.imgFab?.setOnClickListener {

        }
    }

    private fun onAddButtonCliked() {
        setVisibility(cliked)
        setAnimation(cliked)
        setClikable(cliked)
        cliked = !cliked
    }

    private fun setAnimation(cliked : Boolean) {
        if (!cliked){
            binding.editFab?.startAnimation(rotateFrom)
            binding.imgFab?.startAnimation(rotateFrom)
            binding.fab.startAnimation(rotateOpen)
        }else{
            binding.editFab?.startAnimation(rotateToBottom)
            binding.imgFab?.startAnimation(rotateToBottom)
            binding.fab.startAnimation(rotateClose)

        }
    }

    private fun setVisibility(cliked : Boolean) {

        if(!cliked){
            binding.editFab?.visibility = View.VISIBLE
            binding.imgFab?.visibility = View.VISIBLE
        }else{
            binding.editFab?.visibility = View.INVISIBLE
            binding.imgFab?.visibility = View.INVISIBLE
        }
    }

    private fun setClikable(cliked: Boolean){
        if (!cliked){
            binding.editFab?.isClickable = true
            binding.imgFab?.isClickable = true
        }else{
            binding.editFab?.isClickable = false
            binding.imgFab?.isClickable = false
        }
    }

    private fun navigateToBottombar(id: Int) {   // Bottom bar ın seçilen id sine göre yönlendiriyor
        val bottomNav =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = id
    }
}