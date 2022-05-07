package com.aplicada2.tareai.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aplicada2.tareai.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplicada2.tareai.data.database.entities.Persona
import com.aplicada2.tareai.iu.viewmodel.PersonaViewModel

import kotlinx.android.synthetic.main.fragment_list.view.*



class ListFragment : Fragment() {

    private lateinit var mPersonaView: PersonaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //recyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //PersonaViewModel
        mPersonaView = ViewModelProvider(this).get(PersonaViewModel::class.java)
        mPersonaView.readAllData.observe(viewLifecycleOwner, Observer { persona ->
            adapter.setData(persona)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //Menu para eliminar todas las personas registradas
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.menu_delete) {
            deleteAllPersona()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteAllPersona() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sí"){_,_ ->
            mPersonaView.deleteAllPersona()
            Toast.makeText(requireContext(), "Se ha eliminado todo correctamente.", Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton("No"){_,_ ->

        }
        builder.setTitle("Estás a punto de eliminar todos los registros")
        builder.setMessage("Estás seguro?")
        builder.create().show()
    }
}