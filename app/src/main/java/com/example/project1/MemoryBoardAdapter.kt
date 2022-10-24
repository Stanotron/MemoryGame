package com.example.project1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.models.BoardSize
import com.example.project1.models.MemoryCard
import kotlin.math.min

class MemoryBoardAdapter(
    private val context: Context,
    private val boardSize: BoardSize,
    private val cards: List<MemoryCard>,
    private val cardClickListener : CardClickListener
) :
    RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {
    companion object{
        private const val TAG = "MemoryBoardAdapter"
        private const val Margin_Size = 10
    }
    interface CardClickListener{
        fun onCardClicked(position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardWidth:Int = parent.width/boardSize.getWidth() - (2* Margin_Size)
        val cardHeight: Int = parent.height/boardSize.getHeight() -(2 * Margin_Size)
        val cardsidelength: Int = min(cardWidth,cardHeight)
        val view = LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)
        val layoutParams = view.findViewById<CardView>(R.id.CardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.height = cardsidelength
        layoutParams.width = cardsidelength
        layoutParams.setMargins(Margin_Size, Margin_Size, Margin_Size, Margin_Size)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = boardSize.numcards

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)
        fun bind(position: Int) {
            val memoryCard = cards[position]
            imageButton.setImageResource(if (memoryCard.isFaceUp) memoryCard.identifier else R.drawable.ic_launcher_background)
            imageButton.alpha = if(memoryCard.isMatched) .4f else 1.0f
            imageButton.setOnClickListener{
                Log.i(TAG,"Clicked on position $position" )
                cardClickListener.onCardClicked(position)
            }
        }
    }

}
