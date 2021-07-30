package com.example.pruebaadn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.DisponibilityEntity
import com.example.domain.value_object.PricesValueObj
import com.example.pruebaadn.R
import com.example.pruebaadn.base.App
import com.example.pruebaadn.databinding.ActivityMainBinding
import com.example.pruebaadn.utils.DialogAddVehicle
import com.example.pruebaadn.utils.createToast
import com.example.pruebaadn.utils.showDialoAddVehicle
import com.example.pruebaadn.view.interfaces.CheckViewModelDelegate
import com.example.pruebaadn.view.interfaces.DisponibilityViewModelDelegate
import com.example.pruebaadn.view.interfaces.PriceViewModelDelegate
import com.example.pruebaadn.view.interfaces.VehicleViewModelDelegate
import com.example.pruebaadn.view.view_model.*

class MainActivity : AppCompatActivity(),
            DisponibilityViewModelDelegate, PriceViewModelDelegate,
            CheckViewModelDelegate, VehicleViewModelDelegate {

    private lateinit var variantInitViewModel: VariantInitViewModel
    private lateinit var disponibilityViewModel: DisponibilityViewModel
    private lateinit var vehicleViewModel: VehicleViewModel
    private lateinit var checkViewModel: CheckViewModel
    private lateinit var priceViewModel: PriceViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var listDisponibility : MutableList<DisponibilityEntity>
    private lateinit var listPrice : MutableList<PricesValueObj>
    private lateinit var listVehicleAggregation : MutableList<VehicleAggregate>
    private var adapter: MainRecyclerViewAdapter = MainRecyclerViewAdapter(this, emptyList<VehicleAggregate>().toMutableList())

    override fun onCreate(savedInstanceState: Bundle?) {
        variantInitViewModel = ViewModelProvider(this).get(VariantInitViewModel::class.java)
        disponibilityViewModel = ViewModelProvider(this).get(DisponibilityViewModel::class.java)
        vehicleViewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        checkViewModel = ViewModelProvider(this).get(CheckViewModel::class.java)
        priceViewModel = ViewModelProvider(this).get(PriceViewModel::class.java)

        disponibilityViewModel.setDelegate(this)
        vehicleViewModel.setDelegate(this)
        checkViewModel.setDelegate(this)
        priceViewModel.setDelegate(this)

        variantInitViewModel.validateDbAndInsertVariablesInit(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.setContext(this)

        onStyleRecycler()
        getAllInit()
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
            checkViewModel.validateCosteVehicle(it)
        }
    }

    private fun getAllInit(){
        disponibilityViewModel.getAllDisponibility(this)
        checkViewModel.getAllCheck(this)
    }

    private fun eventAddVehicle(){
        binding.btnAddVehicle.setOnClickListener {
            dialogAddVehicle()
        }
    }

    private fun dialogAddVehicle(){
        DialogAddVehicle
            .getInstance()
            .withActionBtnOk {
                validateDisponibility(it.typeId!!)
                vehicleViewModel.insertVehicleDB(this, it)
                updateDisponibility(it.typeId!!)
            }
        this.showDialoAddVehicle()
    }

    private fun validateDisponibility(type: Int){
        if (!disponibilityViewModel.validateDisponibility(listDisponibility, type)) {
            binding.recyclerViewSearchResults.post {
                App.getContext()?.createToast(getString(R.string.not_disponibility))
            }
            return
        }
    }

    private fun updateDisponibility(type: Int){
        when(type){
            1 -> {
                listDisponibility[0].count = listDisponibility[0].count!! -1
                disponibilityViewModel.updateDisponibility(listDisponibility[0])
            }
            2 -> {
                listDisponibility[1].count = listDisponibility[1].count!! -1
                disponibilityViewModel.updateDisponibility(listDisponibility[1])
            }
        }
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

    override fun responseGetAllDisponibility(list: MutableList<DisponibilityEntity>) {
        listDisponibility = list
    }

    override fun responseEmptyAllCheck() {
        adapter.setData(emptyList<VehicleAggregate>().toMutableList())
//        App.getContext()?.createToast(getString(R.string.list_is_empty))
    }

    override fun responseGetAllCheck(list: MutableList<VehicleAggregate>) {
        adapter.setData(list)
        listVehicleAggregation = list
    }

    override fun responseAllPrice(list: MutableList<PricesValueObj>) {
        listPrice = list
    }
}