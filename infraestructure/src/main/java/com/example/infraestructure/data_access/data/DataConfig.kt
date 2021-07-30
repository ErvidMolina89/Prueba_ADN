package com.example.infraestructure.data_access.data

import com.example.infraestructure.data_access.models.variant_init.DisponibilityModels
import com.example.infraestructure.data_access.models.variant_init.PricesModels
import com.example.infraestructure.data_access.models.variant_init.TypePriceModels
import com.example.infraestructure.data_access.models.variant_init.TypeVehicleModels

class DataConfig {

    fun inserTypePrice(): MutableList<TypePriceModels>{
        val list = emptyList<TypePriceModels>().toMutableList()
        list.add(TypePriceModels(1, "Dia"))
        list.add(TypePriceModels(2, "Hora"))
        return list
    }

    fun inserTypeVehicle(): MutableList<TypeVehicleModels>{
        val list = emptyList<TypeVehicleModels>().toMutableList()
        list.add(TypeVehicleModels(1, "Carros"))
        list.add(TypeVehicleModels(2, "Motos"))
        return list
    }

    fun inserPrice(): MutableList<PricesModels> {
        val list = emptyList<PricesModels>().toMutableList()
        list.add(PricesModels(1, 1, 1, 8000.00, null))
        list.add(PricesModels(1, 1, 2, 1000.00, null))
        list.add(PricesModels(1, 2, 1, 4000.00, 2000.00))
        list.add(PricesModels(1, 2, 2, 500.00, 2000.00))
        return list
    }

    fun inserDisponibility(): MutableList<DisponibilityModels>{
        val list = emptyList<DisponibilityModels>().toMutableList()
        list.add(DisponibilityModels(1, 1, 20))
        list.add(DisponibilityModels(2, 2, 10))
        return list
    }
}