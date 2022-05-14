package com.aplicada2.tareai.fragments.ocupacion.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aplicada2.tareai.R
import com.aplicada2.tareai.data.database.entities.Ocupacion
import com.aplicada2.tareai.iu.viewmodel.OcupacionViewModel
import kotlinx.android.synthetic.main.fragment_add_ocupacion.*
import kotlinx.android.synthetic.main.fragment_add_ocupacion.view.*

class addFragmentOcupacion : Fragment() {

    private lateinit var mOcupacionViewModel: OcupacionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_ocupacion, container, false)

        mOcupacionViewModel = ViewModelProvider(this).get(OcupacionViewModel::class.java)

        view.add_btn_ocupacion.setOnClickListener{
            insertDataToDatabase()
        }

        return  view
    }

    private fun insertDataToDatabase() {

        if(addNombres_et_ocupacion.text.toString().length > 2){
            val nombre = addNombres_et_ocupacion.text.toString()

            //Se crea la persona
            val ocupacion = Ocupacion(0, nombre)

            //Se manda a la baase de datos
            mOcupacionViewModel.addOcupacion(ocupacion)
            Toast.makeText(requireContext(),getString(R.string.AgregarSinErrores), Toast.LENGTH_LONG).show()

            //Ir atras
            findNavController().navigate(R.id.action_addFragmentOcupacion_to_listFragmentOcupacion)
        }else{
            Toast.makeText(requireContext(),getString(R.string.AgregarConErrores), Toast.LENGTH_LONG).show()
        }
    }
}