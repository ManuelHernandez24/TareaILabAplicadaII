package com.aplicada2.tareai.fragments.ocupacion.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplicada2.tareai.R
import com.aplicada2.tareai.fragments.ocupacion.list.ListAdapterOcupacion
import com.aplicada2.tareai.iu.viewmodel.OcupacionViewModel
import com.aplicada2.tareai.iu.viewmodel.PersonaViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_list_ocupacion.view.*

class ListFragmentOcupacion : Fragment() {

    private lateinit var mOcupacionViewModel: OcupacionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list_ocupacion, container, false)

        //recyclerView
        val adapter = ListAdapterOcupacion()
        val recyclerView = view.recyclerviewOcupacion
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //PersonaViewModel
        mOcupacionViewModel = ViewModelProvider(this).get(OcupacionViewModel::class.java)
        mOcupacionViewModel.readAllData.observe(viewLifecycleOwner, Observer { ocupacion ->
            adapter.setData(ocupacion)
        })

        view.floatingActionButtonOcupacion.setOnClickListener{
            findNavController().navigate(R.id.action_listFragmentOcupacion_to_addFragmentOcupacion)
        }

        view.floatingActionButtonP.setOnClickListener{
            findNavController().navigate(R.id.action_listFragmentOcupacion_to_listFragment)
        }

        //Menu para eliminar todas las personas registradas
        setHasOptionsMenu(true)

        return view
    }
/*
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
    */

}