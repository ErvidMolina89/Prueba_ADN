package com.example.pruebaadn.view.exit_vehicle

import androidx.lifecycle.ViewModel
import com.example.pruebaadn.base.App
import com.example.pruebaadn.data_source.data_access.local_db.entities.CheckModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.ConsultCheckAndVehicle
import com.example.pruebaadn.data_source.data_access.local_db.entities.DetailsVehicleModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.PricesModels
import com.example.pruebaadn.data_source.repositories.RepoCheck
import com.example.pruebaadn.data_source.repositories.RepoSynchronization
import com.example.pruebaadn.data_source.repositories.RepoVehicle
import com.example.pruebaadn.uses_case.GetConsultCheckAndVehicleUseCase
import java.util.*
import javax.inject.Inject

interface ExitViewModelDelegate {
    fun responseNotCheckForId()
    fun responseConsultCheckAndVehicle(check: ConsultCheckAndVehicle)
    fun responseDetailVehicleForVehicleId(detailVehicle: DetailsVehicleModels)
    fun responsePricesWithDetails(list: List<PricesModels>)
    fun responsePricesNotDetails(list: List<PricesModels>)

}

class ExitViewModel : ViewModel() {

    @Inject
    lateinit var getConsultCheckAndVehicleUseCase: GetConsultCheckAndVehicleUseCase
    private var repoVehicle = RepoVehicle()
    private var repoCheck = RepoCheck()
    private var repo = RepoSynchronization()
    private var cilindreBoolean = false
    private var updateCheck = false
    private var consultCheckAndVehicle : ConsultCheckAndVehicle? = null
    private var delegate : ExitViewModelDelegate? = null

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    fun getConsultCheckAndVehicle(id: Int){
        updateCheck = false
        getConsultCheckAndVehicleUseCase.invoke(id, {
            if (updateCheck) return@invoke
            consultCheckAndVehicle = it
            delegate?.responseConsultCheckAndVehicle(it)
            cilindreBoolean = false
            if(it.vehicleModels?.typeId == 2) {
                cilindreBoolean = true
                getDetailVehicleForVehicleId(it.vehicleModels?.typeId!!)
                getPricesForTypeId(it.vehicleModels?.typeId!!)
            }else getPricesForTypeId(it.vehicleModels?.typeId!!)
        }, {
            delegate?.responseNotCheckForId()
        })

    }

    private fun getPricesForTypeId(id: Int){
        repo.getPricesForTypeId(id).observeForever {
            if (cilindreBoolean){
                delegate?.responsePricesWithDetails(it)
            } else delegate?.responsePricesNotDetails(it)
        }
    }

    private fun getDetailVehicleForVehicleId(id: Int){
        repoVehicle.getDetailVehicleForVehicleId(id).observeForever {
            delegate?.responseDetailVehicleForVehicleId(it)
        }
    }

    fun updateCheck(check: CheckModels){
        repoCheck.onUpdateCheck(check)
        updateCheck = true
    }

    fun validateCosteHoursCeroForType(list: List<PricesModels>, cylindre: Int?) : Int{
        var price = 0
        if (cilindreBoolean){
            price = validateCylinderHoursCero(list, cylindre!!)
        } else {
            if(list[0].typePrice == 2){
                price = list[0].value?.toInt()!!
            }else {
                price = list[1].value?.toInt()!!
            }
        }
        return price
    }

    private fun validateCylinderHoursCero(list: List<PricesModels>, cylindre: Int): Int{

        if (cylindre >= 500){
            if (list[0].typePrice == 2){
                return (list[0].value!! + list[0].extra?.toInt()!!).toInt()
            }else {
                return (list[1].value!! + list[1].extra?.toInt()!!).toInt()
            }
        }else{
            if (list[0].typePrice == 2){
                return list[0].value!!.toInt()
            }else {
                return list[1].value!!.toInt()
            }
        }
    }

    fun validateCoste(day: Int, hours: Int, listPrice: List<PricesModels>): Int {
        var costeDay = 0
        var costeHours = 0

        listPrice.forEach {
            when(it.typePrice){
                1 -> {
                    if (hours >= 9) {
                        costeDay = it.value!!.toInt() * (day+1)
                        return@forEach
                    }else {
                        costeDay = it.value!!.toInt() * day
                        return@forEach
                    }
                }
                2 -> {
                    if(hours < 9) costeHours = it.value!!.toInt() * hours
                }
            }
        }
        return (costeDay + costeHours)
    }

    fun setDelegate(delegate: ExitViewModelDelegate) {
        this.delegate = delegate
    }

}