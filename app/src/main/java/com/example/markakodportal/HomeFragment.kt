package com.example.markakodportal

import ImageDialog
import MessageAdapter
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.markakodportal.Dataclass.Message

import com.example.markakodportal.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false
    private var messageList: MutableList<String> = mutableListOf()


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

        view.translationY = view.height.toFloat()
        view.alpha = 0f
        view.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(1000)
            .start()

        val slideList = arrayListOf(
            SlideModel(
                "https://markakod.com/wp-content/uploads/2023/05/aselsan-1.png",
                "Aselsan"
            ),
            SlideModel(
                "https://markakod.com/wp-content/uploads/2023/06/EH-3.png",
                "English Home"
            ),
            SlideModel(
                "https://markakod.com/wp-content/uploads/2023/05/porshe-club.png",
                "Porsche Club"
            ),
            SlideModel(
                "https://markakod.com/wp-content/uploads/2023/06/My-project-1-18.png",
                "Holi"
            )
        )
        val requestOptions = RequestOptions()
            .centerCrop() // Ölçekleme tipi

        val imageList = ArrayList<SlideModel>()

        for (slide in slideList) {
            val imageUrl = slide.imageUrl // Slaytın resim URL'sini al
            val title = slide.title // Slaytın başlığını al

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
                        if (imageList.size == slideList.size) {
                            binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
                        }
                        binding.imageSlider.setItemClickListener(object : ItemClickListener {
                            override fun onItemSelected(position: Int) {
                                when (position) {
                                    0 -> findNavController().navigate(R.id.action_homeFragment_to_sliderDescriptionFragment)
                                    1 -> findNavController().navigate(R.id.action_homeFragment_to_sliderDescriptionFragment)
                                    2 -> findNavController().navigate(R.id.action_homeFragment_to_sliderDescriptionFragment)
                                    3 -> findNavController().navigate(R.id.action_homeFragment_to_sliderDescriptionFragment)
                                }
                                println("pozisyon: $position")
                            }

                            override fun doubleClick(position: Int) {
                                println("double click")
                            }
                        })
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        }
            binding.imgTwitter.setOnClickListener {
               Twittertodirect()
            }
            binding.imgInstagram.setOnClickListener {
                Instagramtodirect()
            }
            binding.imgFacebook.setOnClickListener {
                Facebooktodirect()
            }
            binding.imgLinkedin.setOnClickListener {
                Linkedintodirect()
            }
            binding.imgThreads.setOnClickListener {
                Threadstodirect()
            }

        binding.btnSend.isEnabled = false // Butonu başlangıçta devre dışı bırak

        binding.btnSend.setOnClickListener {
            val statusText = binding.editTextText.text.toString().trim()

            if (statusText.isNotEmpty()) {
                val action = HomeFragmentDirections.actionHomeFragmentToSocailNetworkFragment(statusText)
                findNavController().navigate(action)

                println("Gönderilen mesaj: $statusText")
            }
        }

        binding.editTextText.addTextChangedListener { // EditText metni değiştiğinde kontrol et
                val statusText = binding.editTextText.text.toString().trim() // EditText içeriğini al ve baştaki ve sondaki boşlukları kaldır
                binding.btnSend.isEnabled = statusText.isNotEmpty() // Düğmeyi EditText'in doluluk durumuna göre etkinleştir veya devre dışı bırak
            }
        }

    private fun navigateToBottombar(id: Int) {   // Bottom bar ın seçilen id sine göre yönlendiriyor
        val bottomNav =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = id

    }

    private fun Instagramtodirect(){

        val instagramUsername = "markakod" // Kullanıcı adını buraya yazın
        val instagramUri = Uri.parse("http://instagram.com/_u/$instagramUsername")
        val instagramIntent = Intent(Intent.ACTION_VIEW, instagramUri)

        // Instagram uygulaması yüklü ise, ilgili kullanıcının profil sayfasına yönlendirme yap
        instagramIntent.setPackage("com.instagram.android")
        if (instagramIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(instagramIntent)
        } else {
            // Eğer Instagram uygulaması yüklü değilse, Instagram'ın web sitesine yönlendir
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/$instagramUsername"))
            startActivity(browserIntent)
        }
    }

    private fun Twittertodirect(){

        val twitterUsername = "markakod"
        val twitterUri = Uri.parse("twitter://user?screen_name=$twitterUsername")

        val twitterIntent = Intent(Intent.ACTION_VIEW, twitterUri)
        twitterIntent.setPackage("com.twitter.android") // Twitter uygulamasının paket adı

        // Twitter uygulaması yüklü ise, Twitter profiline yönlendirme yap
        if (twitterIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(twitterIntent)
        }else {
                // Twitter'ın web sitesine yönlendir
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/$twitterUsername"))
            startActivity(browserIntent)
        }
    }

    private fun Facebooktodirect(){

        val facebookUsername = "markakod"
        val facebookUri = Uri.parse("facebook://user?screen_name=$facebookUsername")

        val facebookIntent = Intent(Intent.ACTION_VIEW, facebookUri)
        facebookIntent.setPackage("com.facebook.android") // facebook uygulamasının paket adı

        // Facebook uygulaması yüklü ise, Twitter profiline yönlendirme yap
        if (facebookIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(facebookIntent)
        }else {
            // Facebook'ın web sitesine yönlendir
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/$facebookUsername"))
            startActivity(browserIntent)
        }
    }

    private fun Linkedintodirect(){

        val linkedinUsername = "markakod"
        val linkedinUri = Uri.parse("linkedin://profile/$linkedinUsername")

        val linkedinIntent  = Intent(Intent.ACTION_VIEW, linkedinUri)
        linkedinIntent .setPackage("com.linkedin.android") // facebook uygulamasının paket adı

        // Linkedin uygulaması yüklü ise, Linkedin profiline yönlendirme yap
        if (linkedinIntent .resolveActivity(requireContext().packageManager) != null) {
            startActivity(linkedinIntent )
        }else {
            // Linkedin'ın web sitesine yönlendir
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/$linkedinUsername"))
            startActivity(browserIntent)
        }
    }

    private fun Threadstodirect(){

        val threadsUsername = "markakod"
        val threadsUri = Uri.parse("threads://profile/$threadsUsername")

        val threadsIntent  = Intent(Intent.ACTION_VIEW, threadsUri)
        threadsIntent .setPackage("com.threads.android") // facebook uygulamasının paket adı

        // Threads uygulaması yüklü ise, Threads profiline yönlendirme yap
        if (threadsIntent .resolveActivity(requireContext().packageManager) != null) {
            startActivity(threadsIntent )
        }else {
            // Threads'in web sitesine yönlendir
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.threads.net/$threadsUsername"))
            startActivity(browserIntent)
        }
    }
}
