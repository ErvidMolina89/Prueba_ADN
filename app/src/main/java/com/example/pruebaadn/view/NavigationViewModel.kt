package com.example.pruebaadn.view

import androidx.lifecycle.ViewModel
import com.example.pruebaadn.base.App
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.DisponibilityModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.PricesModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypePriceModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypeVehicleModels
import com.example.pruebaadn.data_source.repositories.RepoSynchronization

class NavigationViewModel : ViewModel() {

    private var repo = RepoSynchronization()

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun insertVariablesInit(){
        repo.getAllTypeVehicle().observeForever {
            if (it.size == 0){
                insertTypePriceInit()
                insertTypeVehicleInit()
            }
        }
    }

    private fun insertTypePriceInit(){
        val array = emptyList<TypePriceModels>().toMutableList()
        var type = TypePriceModels()
        type.name = "Dia"
        array.add(type)

        type = TypePriceModels()
        type.name = "Hora"
        array.add(type)

        repo.onInsertListTypePrice(array)
    }

    private fun insertTypeVehicleInit(){
        val array = emptyList<TypeVehicleModels>().toMutableList()
        var type = TypeVehicleModels()
        type.name = "Carros"
        array.add(type)

        type = TypeVehicleModels()
        type.name = "Motos"
        array.add(type)

        repo.onInsertListTypeVehicle(array, ::onSuccessInsert)
    }

    private fun onSuccessInsert(list: List<Long>){
        list.forEach {
            it
        }
        insertPricesInit()
        insertDisponibilityInit()
    }

    private fun insertDisponibilityInit(){
        val array = emptyList<DisponibilityModels>().toMutableList()
        var item = DisponibilityModels()
        item.typeId = 1
        item.count = 20
        array.add(item)

        item = DisponibilityModels()
        item.typeId = 2
        item.count = 10
        array.add(item)

        repo.onInsertListDisponibility(array)
    }

    private fun insertPricesInit(){
        val array = emptyList<PricesModels>().toMutableList()

        var price = PricesModels()
        price.typeId = 1
        price.typePrice = 1
        price.value = 8000.toString().toDouble()
        array.add(price)

        price = PricesModels()
        price.typeId = 1
        price.typePrice = 2
        price.value = 1000.toString().toDouble()
        array.add(price)

        price = PricesModels()
        price.typeId = 2
        price.typePrice = 1
        price.value = 4000.toString().toDouble()
        price.extra = 2000.toString().toDouble()
        array.add(price)

        price = PricesModels()
        price.typeId = 2
        price.typePrice = 2
        price.value = 500.toString().toDouble()
        price.extra = 2000.toString().toDouble()
        array.add(price)

        repo.onInsertListPrices(array)
    }
}