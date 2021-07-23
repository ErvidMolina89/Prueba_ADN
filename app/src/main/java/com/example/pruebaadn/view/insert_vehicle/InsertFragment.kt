package com.example.pruebaadn.view.insert_vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.pruebaadn.R
import com.example.pruebaadn.base.App
import com.example.pruebaadn.data_source.data_access.local_db.entities.VehicleModels
import com.example.pruebaadn.databinding.FragmentInsertBinding
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.DisponibilityModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypeVehicleModels
import com.example.pruebaadn.utils.createToast
import com.example.pruebaadn.utils.hideKeyboard
import com.example.pruebaadn.utils.validateText
import java.util.*
import javax.inject.Inject

class InsertFragment : Fragment(), InsertViewModelDelegate {

    @Inject
    lateinit var insertViewModel: InsertViewModel
    private var _binding: FragmentInsertBinding? = null
    private var typeVehicle: Int? = null
    private var vehicle: VehicleModels? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (App.getContext() as App).getComponentApplication()?.inject(this)
        insertViewModel.setDelegate(this)
        insertViewModel.getAllTypeVehicle()

        _binding = FragmentInsertBinding.inflate(inflater, container, false)
        val root: View = binding.root
        validateSelectSpinner()
        listenerActionButton()

        return root
    }

    private fun createAdapterSpinner(list: MutableList<TypeVehicleModels>){
         val adapter = ArrayAdapter(
            App.getContext()!!,
            android.R.layout.simple_spinner_item,
            list
         )
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _binding?.spinnerVehicleInsert?.adapter = adapter
    }

    private fun listenerActionButton(){
        _binding?.buttonInsertVehicle?.setOnClickListener {
            vehicle = VehicleModels()
            vehicle?.plate = _binding?.editTextInsert?.text?.toString()
            vehicle?.typeId = typeVehicle
            insertViewModel.validateFielsVehicule(vehicle!!)
            insertViewModel.getVehicleForPlate(vehicle!!)
            _binding?.editTextInsert!!.post {
                App.getContext()?.hideKeyboard(_binding?.editTextInsert!!)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun responseTypeVehicleViewModel(list : MutableList<TypeVehicleModels>){
        createAdapterSpinner(list)
    }

    override fun responseFielsVehicule(mess: String) {
        App.getContext()?.createToast(mess)
        return
    }

    override fun responseCreateCheck(id: Int) {
        _binding?.textViewCheckInsert?.post {
            _binding?.textViewCheckInsert?.text = id.toString()
        }
    }

    override fun responseNotVehicle() {
        if (typeVehicle == 2) {
            val stringCilinder = _binding?.editTextInsertCilinder?.text?.toString()
            if (!stringCilinder?.validateText()!!) {
                App.getContext()?.createToast(getString(R.string.not_cylinder))
                return
            }
            insertViewModel.createVehicleWithDetail(vehicle!!, stringCilinder)
            _binding?.editTextInsertCilinder?.post {
                App.getContext()?.hideKeyboard(_binding?.editTextInsertCilinder!!)
            }
            _binding?.editTextInsertCilinder?.setText("")
        }else {
            insertViewModel.createVehicle(vehicle!!)
            _binding?.editTextInsert?.setText("")
        }
    }

    private fun validateSelectSpinner(){
        _binding?.spinnerVehicleInsert?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                typeVehicle = position + 1
                if(typeVehicle == 2) {
                    _binding?.textInputLayoutInsertCilinder?.visibility = View.VISIBLE
                }else {
                    _binding?.textInputLayoutInsertCilinder?.visibility = View.GONE
                }
                insertViewModel.getDisponibilityModelsTypeId(typeVehicle!!)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}