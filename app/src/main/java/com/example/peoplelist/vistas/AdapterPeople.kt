package com.example.peoplelist.vistas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.peoplelist.data.local.PersonaEntity
import com.example.peoplelist.databinding.ItemPeopleBinding

class AdapterPeople: RecyclerView.Adapter<AdapterPeople.ItemPeopleViewHolder>() {

    lateinit var binding: ItemPeopleBinding
    private val listItemPeople = mutableListOf<PersonaEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPeopleViewHolder {
        binding = ItemPeopleBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ItemPeopleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItemPeople.size
    }

    override fun onBindViewHolder(holder: ItemPeopleViewHolder, position: Int) {
        val people = listItemPeople[position]
        holder.bind(people)
    }

    fun setData(persona: List<PersonaEntity>){
        this.listItemPeople.clear()
        this.listItemPeople.addAll(persona)
        notifyDataSetChanged()
    }

    class ItemPeopleViewHolder(val peopleVista: ItemPeopleBinding): RecyclerView.ViewHolder(peopleVista.root) {
        fun bind(persona : PersonaEntity){
            peopleVista.txtName.text = persona.title +". "+persona.first + " " +persona.last
            peopleVista.txtPhone.text = "Mail: " + persona.email
            peopleVista.txtEdad.text = "Age: " + persona.age.toString()
            peopleVista.txtLocation.text  = "Country: "+ persona.country
            peopleVista.imagenTerreno.load(persona.large)
        }
    }
}