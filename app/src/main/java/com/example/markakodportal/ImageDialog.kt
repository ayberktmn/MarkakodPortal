import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.markakodportal.R

class ImageDialog(context: Context, private val imageUrl: String) : Dialog(context) {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_dialog)

        // Diyalog içindeki ImageView öğesini alın ve resmi yükleyin
        val imageView = findViewById<ImageView>(R.id.imgPerson)
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(imageView)

        // İsteğe bağlı olarak resmin büyütülmesini iptal etmek için diyalogun dışına tıklamayı kapatın
        setCanceledOnTouchOutside(true)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        // İsteğe bağlı olarak diyalogu tam ekrana getirmek için ayarlayın
        window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}
