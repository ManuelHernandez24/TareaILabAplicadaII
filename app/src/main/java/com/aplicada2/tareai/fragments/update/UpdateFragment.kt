package com.aplicada2.tareai.fragments.update

import android.app.AlertDialog
import android.hardware.camera2.TotalCaptureResult
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aplicada2.tareai.R
import com.aplicada2.tareai.data.database.entities.Persona
import com.aplicada2.tareai.iu.viewmodel.PersonaViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mPersonaViewModel: PersonaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mPersonaViewModel = ViewModelProvider(this).get(PersonaViewModel::class.java)

        view.updateNombres_et.setText(args.currentPersona.Nombres)
        view.updateBalance_et.setText(args.currentPersona.Balance.toString())

        view.update_btn.setOnClickListener{
            updateItem()
        }
        //Poner el menu de eliminar
        setHasOptionsMenu(true)



        return view
    }

    private fun updateItem(){
        val nombres = updateNombres_et.text.toString()
        val balance = (updateBalance_et.text.toString()).toDouble()

        if(inputCheck(nombres,updateBalance_et.text)){
            //Se crea la persona
            val updatePersona = Persona(args.currentPersona.PersonaId, nombres, balance)
            //Update current persona
            mPersonaViewModel.updatePersona(updatePersona)
            Toast.makeText(requireContext(), "Se actualizó correctamente", Toast.LENGTH_SHORT).show()
            //Ir atras
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "No puede dejar campos vacios.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nombres: String, balance: Editable): Boolean{
        return !(TextUtils.isEmpty(nombres) && balance.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deletePersona()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePersona() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sí"){_,_ ->
            mPersonaViewModel.deletePersona(args.currentPersona)
            Toast.makeText(requireContext(), "${args.currentPersona.Nombres} ha sido eliminado correctamente", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setPositiveButton("No"){_,_ ->

        }
        builder.setTitle("Estás a punto de elimar a ${args.currentPersona.Nombres}?")
        builder.setMessage("Estás seguro de que quieres eliminar a ${args.currentPersona.Nombres}")
        builder.create().show()
    }
}