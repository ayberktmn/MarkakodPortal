package com.example.markakodportal

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.markakodportal.databinding.FragmentPersonsBinding
import com.example.markakodportal.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.txtPersons.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_personsFragment)
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }

        binding.txtAboutUs.setOnClickListener {
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNav.selectedItemId = R.id.aboutus
        }

        binding.txtCompanycontact.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_contactFragment)
        }
        binding.txtExit.setOnClickListener {
            showLogoutDialog()
        }
    }
    private fun showLogoutDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle(R.string.alert_title)

        alertDialogBuilder.setMessage(getString(R.string.alert_message))
        alertDialogBuilder.setPositiveButton(R.string.positive_button) { dialog, _ ->
            // Çıkış yapılacak işlemler
             requireActivity().finishAffinity()

        }

        alertDialogBuilder.setNegativeButton(R.string.negative_button) { dialog, _ ->
            // İptal edildiğinde yapılacak işlemler
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(Color.RED)
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(Color.BLACK)

        }
        alertDialog.show()
    }
}
