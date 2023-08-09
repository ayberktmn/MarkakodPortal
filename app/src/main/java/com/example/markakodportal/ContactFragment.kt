package com.example.markakodportal

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.example.markakodportal.databinding.FragmentContactBinding
import com.example.markakodportal.databinding.LoadingDialogBinding


class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentContactBinding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cikis = requireActivity().findViewById<ImageView>(R.id.imgback)

        cikis.visibility = View.VISIBLE

        cikis.setOnClickListener {
            findNavController().navigateUp()
            cikis.visibility = View.GONE
        }

        val loadingDialog = ProgressDialog(requireContext())
        loadingDialog.show()
        loadingDialog.setContentView(R.layout.loading_dialog)
        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // Arka planı şeffaf yap
        loadingDialog.setCancelable(false)

        binding.linearWebvisit.setOnClickListener {
            val url = "https://markakod.com/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        binding.animationWeb.setOnClickListener {
            val url = "https://markakod.com/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)

        }

        binding.animationTel.setOnClickListener {
            val phoneNumber = binding.txtTelephone.text
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        }

        binding.animationEmail.setOnClickListener{
            val recipientEmail = arrayOf(binding.txtEmail.text.toString()) // Alıcının e-posta adresini buraya girin
            val subject = "Konu ?" // E-posta konusunu buraya girin
            val message = "Mesajınız ?" // E-posta mesajını buraya girin

            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, recipientEmail)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            emailIntent.putExtra(Intent.EXTRA_TEXT, message)

            startActivity(Intent.createChooser(emailIntent, "E-posta Gönder"))
        }

        binding.animationAdres.setOnClickListener {

                val address = binding.txtAdress.text.toString()

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$address"))
                intent.setPackage("com.google.android.apps.maps") // Google Haritalar uygulamasını zorunlu kıl

                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                } else {
                    // Google Haritalar uygulaması yüklü değilse tarayıcıda açılıcak.
                    val mapUrl = "https://www.google.com/maps/search/?api=1&query=$address"
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
                    startActivity(browserIntent)

            }
        }

        val webView = binding.webView
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    loadingDialog.dismiss()
                } else {
                    loadingDialog.show()
                }
            }
        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loadingDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadingDialog.dismiss()
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }

        val url = "https://markakod.com/iletisim/"
        webView.loadUrl(url)
    }
}