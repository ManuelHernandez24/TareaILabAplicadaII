package com.aplicada2.tareai.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


        return view
    }


}