package com.aplicada2.tareai.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aplicada2.tareai.R
import com.aplicada2.tareai.data.database.entities.Persona
import com.aplicada2.tareai.iu.viewmodel.PersonaViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mPersonaViewModel: PersonaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mPersonaViewModel = ViewModelProvider(this).get(PersonaViewModel::class.java)

        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }

        return  view
    }

    private fun insertDataToDatabase() {
        val nombres = addNombres_et.text.toString()
        val balance = addBalance_et.text


        if(inputCheck(nombres, balance)){
            //Se crea la persona
            val persona = Persona(0, nombres, (balance.toString()).toDouble())

            //Se manda a la baase de datos
            mPersonaViewModel.addPersona((persona))
            Toast.makeText(requireContext(),getString(R.string.AgregarSinErrores), Toast.LENGTH_LONG).show()

            //Ir atras
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),getString(R.string.AgregarConErrores), Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(nombres: String, balance:Editable): Boolean{
        return !(TextUtils.isEmpty(nombres) && balance.isEmpty())
    }
}