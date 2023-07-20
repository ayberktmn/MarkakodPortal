package com.example.markakodportal

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.markakodportal.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }
        // Animasyonu başlat
        binding.animationLinkedln.playAnimation()

        // Animasyonu 0.7 saniye sonra durdurmak için zamanlayıcı oluştur
        object : CountDownTimer(990, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Her saniye için bir şey yapmak isterseniz buraya yazabilirsiniz
            }

            override fun onFinish() {
                // Animasyonu durdur
                binding.animationLinkedln.pauseAnimation()
            }
        }.start()


        val imageUrlList = listOf(
            "https://images.unsplash.com/photo-1621318164984-b06589834c91?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
        )

        val titleList = listOf(
            "Bitkiler",
            "Manzara",
            "Doğa",
            "Uçak"

        )

        binding.button.isEnabled = false // Düğmeyi başlangıçta devre dışı bırak

        binding.button.setOnClickListener {
            val statusText = binding.editTextText.text.toString().trim() // EditText içeriğini al ve baştaki ve sondaki boşlukları kaldır

            if (statusText.isNotEmpty()) { // EditText boş değilse
                Toast.makeText(requireContext(), "Durumunuz Paylaşıldı", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_homeFragment_to_socailNetworkFragment)
            }
        }

        binding.editTextText.addTextChangedListener { // EditText metni değiştiğinde kontrol et
            val statusText = binding.editTextText.text.toString().trim() // EditText içeriğini al ve baştaki ve sondaki boşlukları kaldır
            binding.button.isEnabled = statusText.isNotEmpty() // Düğmeyi EditText'in doluluk durumuna göre etkinleştir veya devre dışı bırak
        }



        val requestOptions = RequestOptions()
            .centerCrop() // Ölçekleme tipi

        val imageList = ArrayList<SlideModel>()

        for (i in imageUrlList.indices) {
            val imageUrl = imageUrlList[i]
            val title = titleList[i]

            Glide.with(requireContext())
                .load(imageUrl)
                .apply(requestOptions)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        val slideModel = SlideModel(imageUrl, title, ScaleTypes.FIT)
                        imageList.add(slideModel)
                        if (imageList.size == imageUrlList.size) {
                            binding.imgSlider.setImageList(imageList, ScaleTypes.FIT)
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        }


    }
}