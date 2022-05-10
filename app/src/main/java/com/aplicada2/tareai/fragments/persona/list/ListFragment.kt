package com.aplicada2.tareai.fragments.persona.list

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
        builder.setPositiveButton(getString(R.string.Si)){ _, _ ->
            mPersonaView.deleteAllPersona()
            Toast.makeText(requireContext(), getString(R.string.EliminarSinErrores), Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(getString(R.string.No)){ _, _ ->

        }
        builder.setTitle(getString(R.string.TituloEliminarTodo))
        builder.setMessage(getString(R.string.MensajeEliminarTodo))
        builder.create().show()
    }
}