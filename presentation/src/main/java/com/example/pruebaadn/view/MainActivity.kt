package com.example.pruebaadn.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.exception.InvalidDataException
import com.example.domain.model.CheckEntity
import com.example.domain.model.VehicleEntity
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

    }

    override fun onResume() {
        super.onResume()
        listenerRecycler()
        listenerEditTextSearch()
        onStyleRecycler()
        eventAddVehicle()
        if(listVehicleAggregation.size == 0){
            checkViewModel.getAllCheck()
            variantInitViewModel.validateDbAndInsertVariablesInit()
        } else adapter.setData(listVehicleAggregation)
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
                    this.createToast(this.getString(e.message!!.toInt()))
                }
            }
        this.showDialoAddVehicle()
    }

    private fun listenerEditTextSearch(){
            binding.editTextSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(p0.toString() != "") {
                        adapter.setData(filterListUser(p0.toString()))
                    }else adapter.setData(listVehicleAggregation)
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
            this.createToast(getString(R.string.list_is_empty))
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
            DialogCostVehicle
                .getInstance()
                .withText(checkEntity.totalCost?.toString()!!)
                .withTypeDialog(DialogCostVehicle.TypeDialog.OK)
                .withActionBtnOk {

                }
            this.showDialoCostVehicle()
            checkViewModel.getAllCheck()
            binding.editTextSearch.setText("")
            this.hideKeyboard(binding.editTextSearch)
        }
    }

    override fun responseInsertExit() {
        checkViewModel.insertCheckVehicle(checkEntity)
    }

    companion object{
        private var listVehicleAggregation : MutableList<VehicleAggregate> = emptyList<VehicleAggregate>().toMutableList()
    }
}