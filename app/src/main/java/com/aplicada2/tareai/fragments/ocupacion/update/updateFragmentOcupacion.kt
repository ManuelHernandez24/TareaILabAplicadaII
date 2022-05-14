package com.aplicada2.tareai.fragments.ocupacion.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aplicada2.tareai.R
import com.aplicada2.tareai.data.database.entities.Ocupacion
import com.aplicada2.tareai.data.database.entities.Persona
import com.aplicada2.tareai.fragments.ocupacion.update.updateFragmentOcupacionArgs
import com.aplicada2.tareai.iu.viewmodel.OcupacionViewModel
import com.aplicada2.tareai.iu.viewmodel.PersonaViewModel
import kotlinx.android.synthetic.main.fragment_update_ocupacion.*
import kotlinx.android.synthetic.main.fragment_update_ocupacion.view.*


class updateFragmentOcupacion : Fragment() {

    private val args by navArgs<updateFragmentOcupacionArgs>()

    private lateinit var mOcupacionViewModel: OcupacionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_update_ocupacion, container, false)

        mOcupacionViewModel = ViewModelProvider(this).get(OcupacionViewModel::class.java)

        view.updateNombres_et_ocupacion.setText(args.currentOcupacion.Nombre)

        view.update_btn_ocupacion.setOnClickListener {
           updateItem()
        }
        //Poner el menu de eliminar
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){


        if(updateNombres_et_ocupacion.text.toString().length > 2 ){

            val nombre = updateNombres_et_ocupacion.text.toString()
            //Se crea la persona
            val updateOcupacion = Ocupacion(args.currentOcupacion.OcupacionId, nombre)
            //Update current persona
            mOcupacionViewModel.updatePersona(updateOcupacion)
            Toast.makeText(requireContext(), getString(R.string.ActualizarSinErrores), Toast.LENGTH_SHORT).show()
            //Ir atras
            findNavController().navigate(R.id.action_updateFragmentOcupacion_to_listFragmentOcupacion)
        }else{
            Toast.makeText(requireContext(), getString(R.string.ActualizarConErrores), Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nombre: String): Boolean{
        return !(TextUtils.isEmpty(nombre))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteOcupacion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteOcupacion() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(R.string.Si){ _, _ ->
            mOcupacionViewModel.deletePersona(args.currentOcupacion)
            Toast.makeText(requireContext(), "${args.currentOcupacion.Nombre} ${R.string.EliminadoConExito}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragmentOcupacion_to_listFragmentOcupacion)
        }
        builder.setNegativeButton(R.string.No){ _, _ ->

        }
        val title : String = ("Eliminar ${args.currentOcupacion.Nombre}")
        builder.setTitle("${title}")
        builder.setMessage("Estás seguro?")
        builder.create().show()
    }
}