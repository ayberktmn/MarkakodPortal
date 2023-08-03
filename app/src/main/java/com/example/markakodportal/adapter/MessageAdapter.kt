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
import com.example.markakodportal.Dataclass.Message
import com.example.markakodportal.R
import com.example.markakodportal.databinding.ItemMessageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MessageAdapter(private val messageList: List<Message>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(private val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        val messageTextView: TextView = itemView.findViewById(R.id.textViewMessage)
        val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.imageView9.setOnClickListener {
            binding.txtComment.visibility = View.VISIBLE
            binding.linearlayoutYorum.visibility = View.VISIBLE
            binding.imgSendButton.visibility = View.VISIBLE

        }
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messageList[position]
        val txtyorum = holder.itemView.findViewById<EditText>(R.id.txtComment)
        val txtyorumm = holder.itemView.findViewById<TextView>(R.id.txtYorum)
        val SendButton = holder.itemView.findViewById<ImageView>(R.id.imgSendButton)
        val linearlayoutYorum = holder.itemView.findViewById<LinearLayout>(R.id.linearlayoutYorum)
        val layoutyorummesaj = holder.itemView.findViewById<LinearLayout>(R.id.layoutyorummesajı)



        holder.messageTextView.text = currentMessage.content
        holder.timestampTextView.text = currentMessage.getFormattedTime()

        SendButton.setOnClickListener {
            val messageText = txtyorum.text.toString()
            txtyorumm.text = messageText
            txtyorumm.visibility = View.VISIBLE
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
