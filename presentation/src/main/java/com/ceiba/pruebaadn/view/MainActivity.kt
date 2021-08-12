package com.ceiba.pruebaadn.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.model.CheckEntity
import com.ceiba.domain.model.VehicleEntity
import com.ceiba.pruebaadn.R
import com.ceiba.pruebaadn.databinding.ActivityMainBinding
import com.ceiba.pruebaadn.utils.*
import com.ceiba.pruebaadn.view.interfaces.CheckViewModelDelegate
import com.ceiba.pruebaadn.view.interfaces.VehicleViewModelDelegate
import com.ceiba.pruebaadn.view.view_model.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
            CheckViewModelDelegate, VehicleViewModelDelegate {

    private val variantInitViewModel: VariantInitViewModel by viewModels()
    private val vehicleViewModel: VehicleViewModel by viewModels()
    private val checkViewModel: CheckViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var checkEntity: CheckEntity
    private var adapter: MainRecyclerViewAdapter = MainRecyclerViewAdapter(this, emptyList<VehicleAggregate>().toMutableList())

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vehicleViewModel.setDelegate(this)
        checkViewModel.setDelegate(this)

        onStyleRecycler()
        listenerRecycler()
        listenerEditTextSearch()
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
        adapter.let {
            adapter.onClickListener {
                val dateExit = Date()
                it.checkEntity?.dateExit = dateExit.convertToFormatString(DateFormats.ISO_8601)
                checkViewModel.validateCosteVehicle(it)
            }
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
            .withDelegateError {
                this.createToast(it)
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
            this.createToast(message!!)
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