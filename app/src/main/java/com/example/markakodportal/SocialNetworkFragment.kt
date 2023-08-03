package com.example.markakodportal

import MessageAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.markakodportal.Dataclass.Message

import com.example.markakodportal.databinding.FragmentSocailNetworkBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class SocialNetworkFragment : Fragment() {
    private var _binding: FragmentSocailNetworkBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false
    private var messageList: MutableList<Message> = mutableListOf() // messageList'i sınıf düzeyinde tanımladık
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSocailNetworkBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }
        initRecycler()

        val args = SocialNetworkFragmentArgs.fromBundle(requireArguments())
        val messageContent = args.message

        println("gelen mesaj:$messageContent")

        val message = Message(messageContent, System.currentTimeMillis(), mutableListOf())
        messageList.add(message) // messageList'e mesajı ekleyin

        messageAdapter.notifyDataSetChanged() // messageAdapter'ı güncelleyin

        println("listemesaj:$messageList")
        // Dikkat: Burada messageList'inizi messageAdapter'a ekleyeceğiniz bir kod satırı eklemeniz gerekiyor
    }

    private fun initRecycler() {
        messageAdapter = MessageAdapter(messageList)
        binding.rcylerMessages.adapter = messageAdapter
        val layoutManager = LinearLayoutManager(context)
        binding.rcylerMessages.layoutManager = layoutManager
    }

    private fun navigateToBottombar(id: Int) {   // Bottom bar ın seçilen id sine göre yönlendiriyor
        val bottomNav =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = id

    }
}
