package com.example.markakodportal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.markakodportal.databinding.FragmentHomeBinding
import com.example.markakodportal.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnLogin.isEnabled = false // Düğmeyi başlangıçta devre dışı bırak

        binding.btnLogin.setOnClickListener {
            val emailText = binding.editTextemail.text.toString().trim() // E-posta EditText içeriğini al ve baştaki ve sondaki boşlukları kaldır
            val passwordText = binding.editTextsifre.text.toString().trim() // Şifre EditText içeriğini al ve baştaki ve sondaki boşlukları kaldır

            if (emailText.isNotEmpty() && passwordText.isNotEmpty()) { // E-posta ve şifre EditText'leri boş değilse
                Toast.makeText(requireContext(), "Hoş Geldiniz", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
        }

        binding.editTextemail.addTextChangedListener { // E-posta EditText metni değiştiğinde kontrol et
            val emailText = binding.editTextemail.text.toString().trim() // E-posta EditText içeriğini al ve baştaki ve sondaki boşlukları kaldır
            val passwordText = binding.editTextsifre.text.toString().trim() // Şifre EditText içeriğini al ve baştaki ve sondaki boşlukları kaldır
            binding.btnLogin.isEnabled = emailText.isNotEmpty() && passwordText.isNotEmpty() // Düğmeyi E-posta ve şifre EditText'lerinin doluluk durumuna göre etkinleştir veya devre dışı bırak
        }

        binding.editTextsifre.addTextChangedListener { // Şifre EditText metni değiştiğinde kontrol et
            val emailText = binding.editTextemail.text.toString().trim() // E-posta EditText içeriğini al ve baştaki ve sondaki boşlukları kaldır
            val passwordText = binding.editTextsifre.text.toString().trim() // Şifre EditText içeriğini al ve baştaki ve sondaki boşlukları kaldır
            binding.btnLogin.isEnabled = emailText.isNotEmpty() && passwordText.isNotEmpty() // Düğmeyi E-posta ve şifre EditText'lerinin doluluk durumuna göre etkinleştir veya devre dışı bırak
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }
    }
}