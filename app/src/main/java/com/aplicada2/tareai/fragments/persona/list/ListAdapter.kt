package com.aplicada2.tareai.fragments.persona.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aplicada2.tareai.R
import com.aplicada2.tareai.data.database.entities.Persona
import kotlinx.android.synthetic.main.custon_row.view.*




class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var personaLista = emptyList<Persona>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custon_row, parent, false))
    }

    override fun getItemCount(): Int {
        return  personaLista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Para unir dos string

        fun concat(s1: String, s2: String): String {
            return s1 + s2
        }

        val currentItem = personaLista[position]
        holder.itemView.id_txt.text = currentItem.PersonaId.toString()
        holder.itemView.nombres_txt.text = currentItem.Nombres
        holder.itemView.balance_txt.text = concat(currentItem.Balance.toString(), "$")

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(persona: List<Persona>){
        this.personaLista = persona
        notifyDataSetChanged()
    }
}