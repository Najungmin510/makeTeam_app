package com.example.maketeam_app.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.RowLookforTeamBinding
import com.example.maketeam_app.model.BoardContent
import com.example.maketeam_app.model.Position

interface ItemClick{
    fun clickBoard(id : Long)
}

class BoardAdapter(private val context: Context, private val itemClick: ItemClick) : RecyclerView.Adapter<BoardAdapter.Holder>() {

    private var dataList : List<BoardContent> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.Holder {
        val binding = RowLookforTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: BoardAdapter.Holder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(boardContent: List<BoardContent>){
        this.dataList = boardContent
        notifyDataSetChanged()
    }

    inner class Holder(val binding : RowLookforTeamBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("MissingInflatedId", "SetTextI18n")
        fun bind(item : BoardContent){
            binding.teamLookTitle.text = item.title
            binding.teamContent.text = item.content
            binding.teamDate.text = item.writeDay

            if(item.position != null){ //비어있지 않을때만 가능
                val pData = item.position
                val inflater = LayoutInflater.from(context)
                val layoutPosition = binding.layoutPositionGroup

                for(p in pData){
                    val addPosition = inflater.inflate(R.layout.row_position_simple_show, layoutPosition, false)
                    val positionView = addPosition.findViewById<TextView>(R.id.text_position_simple)

                    positionView.text = "${p.positionName}(${p.positionPeople})"
                    layoutPosition.addView(addPosition)
                }
            }

            binding.layoutPreviewBoard.setOnClickListener {
                itemClick.clickBoard(item.id)
            }
        }

    }

}