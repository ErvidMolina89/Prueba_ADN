package com.example.pruebaadn.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.domain.entity.VehicleEntity
import com.example.domain.exception.InvalidDataException
import com.example.pruebaadn.R
import com.example.pruebaadn.base.App
import com.example.pruebaadn.databinding.ActivityMainBinding
import com.example.pruebaadn.utils.*
import com.example.pruebaadn.view.interfaces.CheckViewModelDelegate
import com.example.pruebaadn.view.interfaces.VehicleViewModelDelegate
import com.example.pruebaadn.view.view_model.*
import java.util.*

class MainActivity : AppCompatActivity(),
            CheckViewModelDelegate, VehicleViewModelDelegate {

    private lateinit var variantInitViewModel: VariantInitViewModel
    private lateinit var vehicleViewModel: VehicleViewModel
    private lateinit var checkViewModel: CheckViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var checkEntity: CheckEntity
    private lateinit var listVehicleAggregation : MutableList<VehicleAggregate>
    private var adapter: MainRecyclerViewAdapter = MainRecyclerViewAdapter(this, emptyList<VehicleAggregate>().toMutableList())

    override fun onCreate(savedInstanceState: Bundle?) {
        variantInitViewModel = ViewModelProvider(this).get(VariantInitViewModel::class.java)
        vehicleViewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        checkViewModel = ViewModelProvider(this).get(CheckViewModel::class.java)

        vehicleViewModel.setDelegate(this)
        checkViewModel.setDelegate(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.setContext(this)

        variantInitViewModel.validateDbAndInsertVariablesInit()
        checkViewModel.getAllCheck()

        onStyleRecycler()
        eventAddVehicle()
        listenerRecycler()
        listenerEditTextSearch()
    }

    private fun onStyleRecycler() {
        binding.recyclerViewSearchResults.let {
            it.layoutManager  = LinearLayoutManager(this)
            this@MainActivity.adapter = MainRecyclerViewAdapter(
                this,
                emptyList<VehicleAggregate>().toMutableList()
            )
            binding.recyclerViewSearchResults.adapter = this@MainActivity.adapter
        }
    }

    private fun listenerRecycler(){
        adapter.onClickListener {
            val dateExit = Date()
            it.checkEntity?.dateExit = dateExit.convertToFormatString(DateFormats.ISO_8601)
            checkViewModel.validateCosteVehicle(it)
        }
    }

    private fun eventAddVehicle(){
        binding.btnAddVehicle.setOnClickListener {
            dialogAddVehicle()
        }
    }

    private fun dialogAddVehicle(){
        DialogAddVehicle
            .getInstance()
            .withActionBtnOk { vehicleEntity, dateInput ->
                checkEntity = CheckEntity()
                try {
                    checkEntity.CheckEntity(vehicleEntity.plate, dateInput, null, null)
                    val vehicle = VehicleEntity()
                    vehicle.VehicleEntity(vehicleEntity.plate!!, vehicleEntity.typeId!!, vehicleEntity.cylinder)
                    vehicleViewModel.insertVehicleDB(vehicle, dateInput)
                }catch (e: InvalidDataException){
                    this.createToast(e.message!!)
                }
            }
        this.showDialoAddVehicle()
    }

    private fun listenerEditTextSearch(){
            binding.editTextSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        adapter.setData(filterListUser(p0.toString()))
                    }

            })
    }

    private fun filterListUser (valor: String) :MutableList<VehicleAggregate>{
        val list = listVehicleAggregation.filter {
            return@filter it.plate?.toLowerCase()?.contains(valor.toLowerCase())!!
        }
        if(list.size ==  0){
            this.createToast(getString(R.string.list_is_empty))
        }
        return list.toMutableList()
    }

    override fun responseEmptyAllCheck() {
        binding.editTextSearch.post {
            adapter.setData(emptyList<VehicleAggregate>().toMutableList())
            App.getContext()?.createToast(getString(R.string.list_is_empty))
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun responseGetAllCheck(list: MutableList<VehicleAggregate>) {
        binding.btnAddVehicle.post {
            adapter.setData(list)
            listVehicleAggregation = list
        }
    }

    override fun responseInsertCheckVehicle() {
        checkViewModel.getAllCheck()
    }

    override fun responseException(message: String?) {
        binding.recyclerViewSearchResults.post {
            App.getContext()?.createToast(message!!)
        }
    }

    override fun responseValidateCosteVehicle(checkEntity: CheckEntity) {
        binding.recyclerViewSearchResults.post {
            App.getContext()?.createToast(checkEntity.totalCost!!.toString())
            checkViewModel.getAllCheck()
            binding.editTextSearch.setText("")
            this.hideKeyboard(binding.editTextSearch)
        }
    }

    override fun responseInsertExit() {
        checkViewModel.insertCheckVehicle(checkEntity)
    }
}