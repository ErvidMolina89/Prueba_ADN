package com.example.pruebaadn.view.insert_vehicle

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pruebaadn.R
import com.example.pruebaadn.base.App
import com.example.pruebaadn.data_source.data_access.local_db.entities.CheckModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.DetailsVehicleModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.VehicleModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.DisponibilityModels
import com.example.pruebaadn.data_source.data_access.local_db.entities.variant_init.TypeVehicleModels
import com.example.pruebaadn.data_source.repositories.RepoCheck
import com.example.pruebaadn.data_source.repositories.RepoSynchronization
import com.example.pruebaadn.data_source.repositories.RepoVehicle
import com.example.pruebaadn.utils.DateFormats
import com.example.pruebaadn.utils.convertToFormatString
import com.example.pruebaadn.utils.createToast
import com.example.pruebaadn.utils.validateText
import java.util.*

interface InsertViewModelDelegate {
    fun responseTypeVehicleViewModel(list : MutableList<TypeVehicleModels>)
    fun responseFielsVehicule(mess: String)
    fun responseCreateCheck(id: Int)
    fun responseNotVehicle()
}

class InsertViewModel : ViewModel() {

    private var repo = RepoSynchronization()
    private var repoVehicle = RepoVehicle()
    private var todayInsert: Date? = null
    private var repoCheck = RepoCheck()
    private var disponibility = DisponibilityModels()
    private var stringDetails = ""
    private var delegate: InsertViewModelDelegate? = null

    init {
        (App.getContext() as App).getComponentApplication()?.inject(this)
    }

    private fun createCheck(item : CheckModels){
        repoCheck.onInsertCheck(item, ::onSuccesInsertCheck)
    }

    fun createVehicle(item: VehicleModels){
        repoVehicle.onInsertVehicle(item, ::onSuccesInsertVehicle)
    }

    fun createVehicleWithDetail(item: VehicleModels, details: String){
        stringDetails = details
        repoVehicle.onInsertVehicle(item, ::onSuccesInsertDetail)
    }

   fun getVehicleForPlate(item: VehicleModels){
        repoVehicle.getVehicleForPlate(item.plate!!).observeForever {
            if (it != null || it?.id != null){
                insertCheck(it.id!!)
            } else delegate?.responseNotVehicle()
        }
    }

    fun getAllTypeVehicle(){
        repo.getAllTypeVehicle().observeForever {
            if (it.size != 0) {
                delegate?.responseTypeVehicleViewModel(it as MutableList<TypeVehicleModels>)
            }
        }
    }

    fun getDisponibilityModelsTypeId(id: Int){
        repo.getDisponibilityModelsTypeId(id).observeForever {
            if (it != null) {
                disponibility = it as DisponibilityModels
            }
        }
    }

    private fun onUpdateDisponibility(item: DisponibilityModels){
        repo.onUpdateDisponibility(item)
    }

    private fun onSuccesInsertVehicle(id: Long){
        insertCheck(id.toInt())
        updateCountDisponibility()
    }

    private fun insertCheck(id: Int){
        todayInsert = Date()
        val checkModels = CheckModels()
        checkModels.vehicleId = id
        checkModels.dateInput = todayInsert?.convertToFormatString(DateFormats.ISO_8601)
        createCheck(checkModels)
    }

    private fun updateCountDisponibility(){
        val item = disponibility
        item.count = disponibility.count!! - 1
        onUpdateDisponibility(item)
    }

    private fun onSuccesInsertCheck(id: Long){
        delegate?.responseCreateCheck(id.toInt())
    }

    private fun onSuccesInsert(id: Long){
        Log.e("", "")
    }

    private fun onSuccesInsertDetail(id: Long){
        val item = DetailsVehicleModels()
        item.vehicleId = id.toInt()
        item.value = stringDetails

        repoVehicle.onInsertDetailsVehicle(item, ::onSuccesInsert)
    }

    fun setDelegate(delegate: InsertViewModelDelegate){
        this.delegate = delegate
    }

    fun validateFielsVehicule(vehicle: VehicleModels){
        if (disponibility.count == 0) {
            delegate?.responseFielsVehicule(App.getContext()!!.getString(R.string.not_disponibility))
        }

        if (!vehicle.plate?.validateText()!!) {
            delegate?.responseFielsVehicule(App.getContext()!!.getString(R.string.not_placa))
        }
    }

}