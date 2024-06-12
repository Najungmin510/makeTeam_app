package com.example.maketeam_app.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.maketeam_app.R
import com.example.maketeam_app.databinding.RowLookforTeamBinding
import com.example.maketeam_app.model.BoardContent
import com.example.maketeam_app.model.Position

interface ItemClick{
    fun clickBoard(id : Long)
    fun clickApplyEnd(id : Long, isEnd : Boolean)
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
        @SuppressLint("MissingInflatedId", "SetTextI18n", "ResourceAsColor")
        fun bind(item : BoardContent){
            binding.teamLookTitle.text = item.title
            binding.teamContent.text = item.content
            binding.teamDate.text = item.writeDay

            if(item.position != null){ //비어있지 않을때만 가능
                val pData = item.position
                val inflater = LayoutInflater.from(context)
                val layoutPosition = binding.layoutPositionGroup

                layoutPosition.removeAllViews()

                for(p in pData){
                    val addPosition = inflater.inflate(R.layout.row_position_simple_show, layoutPosition, false)
                    val positionView = addPosition.findViewById<TextView>(R.id.text_position_simple)

                    positionView.text = "${p.positionName}(${p.positionPeople})"
                    layoutPosition.addView(addPosition)
                }
            }

            if(item.isEnd){
                binding.teamSituation.setBackgroundResource(R.drawable.background_looking_for_team_close)
                binding.teamSituation.text = "모집완료"
                binding.teamSituation.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main_color))

            } else {
                binding.teamSituation.setBackgroundResource(R.drawable.background_looking_for_team)
                binding.teamSituation.text = "모집중"
                binding.teamSituation.setTextColor(ContextCompat.getColor(binding.root.context, R.color.text_green))
            }


            binding.layoutPreviewBoard.setOnClickListener {
                itemClick.clickBoard(item.id)
            }
            binding.layoutPreviewBoard.setOnLongClickListener {
                itemClick.clickApplyEnd(item.id, item.isEnd)
                return@setOnLongClickListener(true)
            }
        }

    }

}