package com.example.pruebaadn.view.insert_vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.entity.TypeVehicleEntity
import com.example.domain.entity.VehicleEntity
import com.example.pruebaadn.base.App
import com.example.pruebaadn.databinding.FragmentInsertBinding
import com.example.pruebaadn.utils.hideKeyboard
import com.example.pruebaadn.view.insert_vehicle.interfaces.GetQueryViewModelDelegate
import com.example.pruebaadn.view.insert_vehicle.interfaces.InsertViewModelDelegate
import com.example.pruebaadn.view.insert_vehicle.view_model.GetQueryViewModel
import com.example.pruebaadn.view.insert_vehicle.view_model.InsertViewModel

class InsertFragment : Fragment(), InsertViewModelDelegate, GetQueryViewModelDelegate {

    private lateinit var insertViewModel: InsertViewModel
    private lateinit var getQueryViewModel: GetQueryViewModel
    private var _binding: FragmentInsertBinding? = null
    private var typeVehicle: Int? = null
    private var vehicle: VehicleEntity? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        insertViewModel =
            ViewModelProvider(this).get(InsertViewModel::class.java)
        getQueryViewModel =
            ViewModelProvider(this).get(GetQueryViewModel::class.java)
        insertViewModel.setDelegate(this)
        getQueryViewModel.setDelegate(this)
        getQueryViewModel.getAllTypeVehicle(App.getContext()!!)

        _binding = FragmentInsertBinding.inflate(inflater, container, false)
        val root: View = binding.root
        validateSelectSpinner()
        listenerActionButton()

        return root
    }

    private fun createAdapterSpinner(list: MutableList<TypeVehicleEntity>){
         val adapter = ArrayAdapter(
            App.getContext()!!,
            android.R.layout.simple_spinner_item,
            list
         )
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _binding?.spinnerVehicleInsert?.post { _binding?.spinnerVehicleInsert?.adapter = adapter }
    }

    private fun listenerActionButton(){
        _binding?.buttonInsertVehicle?.setOnClickListener {
            vehicle?.VehicleEntity(_binding?.editTextInsert?.text?.toString()!!, typeVehicle!!, null)
//            insertViewModel.validateFielsVehicule(vehicle!!)
//            insertViewModel.getVehicleForPlate(vehicle!!)
            _binding?.editTextInsert!!.post {
                App.getContext()?.hideKeyboard(_binding?.editTextInsert!!)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun responseGetAllTypeVehicle(list : MutableList<TypeVehicleEntity>){
        createAdapterSpinner(list)
    }

//    override fun responseFielsVehicule(mess: String) {
//        App.getContext()?.createToast(mess)
//        return
//    }
//
//    override fun responseCreateCheck(id: Int) {
//        _binding?.textViewCheckInsert?.post {
//            _binding?.textViewCheckInsert?.text = id.toString()
//        }
//    }
//
//    override fun responseNotVehicle() {
//        if (typeVehicle == 2) {
//            val stringCilinder = _binding?.editTextInsertCilinder?.text?.toString()
//            if (!stringCilinder?.validateText()!!) {
//                App.getContext()?.createToast(getString(R.string.not_cylinder))
//                return
//            }
//            insertViewModel.createVehicleWithDetail(vehicle!!, stringCilinder)
//            _binding?.editTextInsertCilinder?.post {
//                App.getContext()?.hideKeyboard(_binding?.editTextInsertCilinder!!)
//            }
//            _binding?.editTextInsertCilinder?.setText("")
//        }else {
//            insertViewModel.createVehicle(vehicle!!)
//            _binding?.editTextInsert?.setText("")
//        }
//    }

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
//                insertViewModel.getDisponibilityModelsTypeId(typeVehicle!!)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}