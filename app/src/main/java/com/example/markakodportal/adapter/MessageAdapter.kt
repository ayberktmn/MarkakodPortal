import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.markakodportal.Dataclass.Message
import com.example.markakodportal.R
import com.example.markakodportal.databinding.ItemMessageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MessageAdapter(var messageList: List<Message>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(private val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        val messageTextView: TextView = itemView.findViewById(R.id.textViewMessage)
        val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)



        var isHidden = true // Başlangıçta gizlenmiş olarak varsayalım

        binding.imgComment.setOnClickListener {
            if (isHidden) {
                // Gösterilecek görünümler
                binding.txtComment.visibility = View.VISIBLE
                binding.linearlayoutYorum.visibility = View.VISIBLE
                binding.imgSendButton.visibility = View.VISIBLE
            } else {
                // Gizlenecek görünümler
                binding.txtComment.visibility = View.GONE
                binding.linearlayoutYorum.visibility = View.GONE
                binding.imgSendButton.visibility = View.GONE
            }

            isHidden = !isHidden // Durumu tersine çevir
        }
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messageList[position]
        val txtyorum = holder.itemView.findViewById<EditText>(R.id.txtComment)
        val txtyorumsend = holder.itemView.findViewById<TextView>(R.id.txtYorum)
        val SendButton = holder.itemView.findViewById<ImageView>(R.id.imgSendButton)
        val linearlayoutYorum = holder.itemView.findViewById<LinearLayout>(R.id.linearlayoutYorum)
        val layoutyorummesaj = holder.itemView.findViewById<LinearLayout>(R.id.layoutyorummesajı)
        val mesajfoto = holder.itemView.findViewById<ImageView>(R.id.imgMessagePhoto)


        holder.messageTextView.text = currentMessage.content
        holder.timestampTextView.text = currentMessage.getFormattedTime()
        currentMessage.comments.add(messageList.size.toString())
        Glide.with(mesajfoto)
            .load("https://media.licdn.com/dms/image/C4D03AQHGK0voeZK66A/profile-displayphoto-shrink_400_400/0/1662224359014?e=1697673600&v=beta&t=Vx0o5zMO-S4TT_TA-U1nO4YMnsGYoMM0AJ9kMv7GES8")
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(mesajfoto)


        SendButton.setOnClickListener {

            val messageText = txtyorum.text.toString()

            txtyorumsend.text = messageText
            txtyorumsend.visibility = View.VISIBLE
            txtyorum.setText("")

            txtyorum.visibility = View.GONE
            linearlayoutYorum.visibility = View.GONE
            SendButton.visibility = View.GONE

            layoutyorummesaj.visibility = View.VISIBLE
        }
    }
    override fun getItemCount(): Int {
        return messageList.size
    }
}
