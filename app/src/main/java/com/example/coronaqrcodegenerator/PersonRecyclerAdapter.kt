package com.example.coronaqrcodegenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaqrcodegenerator.models.Person

class PersonRecyclerAdapter(private var items: List<Person>) :
    RecyclerView.Adapter<PersonRecyclerAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
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

    class PersonViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val person_name: TextView = itemView.findViewById(R.id.person_name)
        val person_phone_number: TextView = itemView.findViewById(R.id.person_phone_number)
    }
}