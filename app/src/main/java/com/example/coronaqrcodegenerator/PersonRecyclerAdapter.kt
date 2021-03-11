package com.example.coronaqrcodegenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaqrcodegenerator.models.Person
import com.example.coronaqrcodegenerator.models.persistence.PersonDbHelper

class PersonRecyclerAdapter(private var personDbHelper: PersonDbHelper) :
    RecyclerView.Adapter<PersonRecyclerAdapter.PersonViewHolder>() {
    private lateinit var items: List<Person>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        items = personDbHelper.findAll()
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_list_single_item, parent, false)

        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.person_name.text = items[position].name
        holder.person_phone_number.text = items[position].phoneNumber
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateRecyclerView() {
        items = personDbHelper.findAll()
        notifyDataSetChanged()
    }

    class PersonViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val person_name: TextView = itemView.findViewById(R.id.person_name)
        val person_phone_number: TextView = itemView.findViewById(R.id.person_phone_number)
    }
}