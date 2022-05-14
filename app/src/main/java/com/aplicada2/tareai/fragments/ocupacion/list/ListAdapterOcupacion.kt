package com.aplicada2.tareai.fragments.ocupacion.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aplicada2.tareai.R
import com.aplicada2.tareai.data.database.entities.Ocupacion
import kotlinx.android.synthetic.main.custon_row_ocupacion.view.*


class ListAdapterOcupacion :RecyclerView.Adapter<ListAdapterOcupacion.MyViewHolder>() {

    private var ocupacionLista = emptyList<Ocupacion>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custon_row_ocupacion, parent, false))
    }

    override fun getItemCount(): Int {
        return  ocupacionLista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Para unir dos string

        val currentItem = ocupacionLista[position]
        holder.itemView.id_txt_ocupacion.text = currentItem.OcupacionId.toString()
        holder.itemView.nombres_txt_ocupacion.text = currentItem.Nombre

        holder.itemView.rowLayoutOcupacion.setOnClickListener{
            val action = ListFragmentOcupacionDirections.actionListFragmentOcupacionToUpdateFragmentOcupacion(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(ocupacion: List<Ocupacion>){
        this.ocupacionLista = ocupacion
        notifyDataSetChanged()
    }
}