package com.example.maketeam_app.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.maketeam_app.R

data class TeamMember(
    val professor: String,
    val name: String,
    val school: String,
    val department: String
)

class TeamAdapter(private val context: Context, private val teamMembers: List<TeamMember>) : BaseAdapter() {

    override fun getCount(): Int = teamMembers.size

    override fun getItem(position: Int): Any = teamMembers[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_team, parent, false)
        val teamMember = teamMembers[position]

        val textViewProfessor = view.findViewById<TextView>(R.id.textViewProfessor)
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewSchool = view.findViewById<TextView>(R.id.textViewSchool)
        val textViewDepartment = view.findViewById<TextView>(R.id.textViewDepartment)

        textViewProfessor.text = teamMember.professor
        textViewName.text = teamMember.name
        textViewSchool.text = teamMember.school
        textViewDepartment.text = teamMember.department

        return view
    }
}
