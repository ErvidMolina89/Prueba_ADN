package com.example.pruebaadn.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.constraintlayout.widget.Guideline
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.domain.aggregate.VehicleAggregate
import com.example.domain.entity.CheckEntity
import com.example.pruebaadn.R
import com.example.pruebaadn.base.App
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class DialogAddVehicle private constructor(): DialogFragment() {

    private val T = "DialogAddVehicle"
    private var vehicleAggregate: VehicleAggregate? = null
    private var checkEntity: CheckEntity? = null
    private var today: Date? = null

    companion object{
        private var showingDialog = false
        @SuppressLint("StaticFieldLeak")
        private var instance : DialogAddVehicle?= null

        fun getInstance() : DialogAddVehicle {
            if(instance == null )
            {
                instance =
                    DialogAddVehicle()
            }
            return instance!!
        }
    }


    private var mainContainer : View?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mainContainer = inflater.inflate(R.layout.dialog_add_vehicle,null,false)
        isCancelable = false

        findsViewElements()
        addListenersButton()
        addListenersRadioButton()
        vehicleAggregate = VehicleAggregate()
        vehicleAggregate?.typeId = 1
        return mainContainer
    }

    private var image_dialog : ImageView?= null
    private var edit_plate : EditText?= null
    private var edit_cylinder : EditText?= null
    private var edit_cylinder_container : TextInputLayout?= null
    private var title : TextView?= null
    private var btn_ok : Button?= null
    private var btn_cancel : Button?= null
    private var radio_car : RadioButton? = null
    private var radio_motocycle : RadioButton? = null
    private var guideline : Guideline?= null


    private fun findsViewElements(){

        image_dialog    = mainContainer?.findViewById(R.id.image_dialogue)
        edit_plate      = mainContainer?.findViewById(R.id.editTextDialoguePlate)
        edit_cylinder   = mainContainer?.findViewById(R.id.editTextDialogueCilinder)
        edit_cylinder_container = mainContainer?.findViewById(R.id.textInputLayoutDialogueCilinder)
        title           = mainContainer?.findViewById(R.id.title_dialogue)
        btn_ok          = mainContainer?.findViewById(R.id.btn_dialogue_accept)
        btn_cancel      = mainContainer?.findViewById(R.id.btn_dialogue_cancel)
        radio_car       = mainContainer?.findViewById(R.id.radio_button_car)
        radio_motocycle = mainContainer?.findViewById(R.id.radio_button_motocycle)
        guideline       = mainContainer?.findViewById(R.id.line_guide)

    }

    private fun addListenersButton(){
        btn_ok?.setOnClickListener {
            enterDateToday()
            if (edit_plate?.text?.toString()?.isEmpty()!!)
                return@setOnClickListener App.getContext()!!.createToast(getString(R.string.not_placa))
            val date = checkEntity?.dateInput?.convertToFormatDate(DateFormats.ISO_8601)
            if (!date?.validateEntryDateVehicle(edit_plate?.text.toString())!!)
                return@setOnClickListener App.getContext()!!.createToast(getString(R.string.not_placa_day))
            when(vehicleAggregate?.typeId){
                1->{ getVehicleCar() }
                2 -> {
                    if (edit_cylinder?.text?.toString()?.isEmpty()!!) {
                        App.getContext()!!.createToast(getString(R.string.not_placa))
                        return@setOnClickListener
                    }
                    getVehicleMotocycle()
                }
            }
            invokesActionOk?.invoke(vehicleAggregate!!)
            dismiss()
            cleanElementsOfSight()
        }
        btn_cancel?.setOnClickListener {
            dismiss()
            cleanElementsOfSight()
        }
    }

    private fun addListenersRadioButton(){
        radio_car?.setOnClickListener {
            edit_cylinder_container?.hide()
            vehicleAggregate?.typeId = 1
        }
        radio_motocycle?.setOnClickListener {
            edit_cylinder_container?.show()
            vehicleAggregate?.typeId = 2
        }
    }

    private fun getVehicleCar(){
        vehicleAggregate?.plate = edit_plate!!.text.toString()
    }

    private fun getVehicleMotocycle(){
        vehicleAggregate?.cylinder = edit_cylinder?.text?.toString()
    }

    private fun enterDateToday(){
        today = Date()
        checkEntity = CheckEntity()
        checkEntity?.dateInput = today?.convertToFormatString(DateFormats.ISO_8601)
        vehicleAggregate?.checkEntity = checkEntity
    }

    private fun cleanElementsOfSight(){
        invokesActionOk = null
        vehicleAggregate = null
        edit_plate?.setText("")
        edit_cylinder?.setText("")
    }

    override fun dismiss() {
        showingDialog = false
        if(fragmentManager == null ){
            return
        }
        super.dismiss()
        super.dismissAllowingStateLoss()
    }


    override fun onStart() {
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            if(showingDialog){ return }
            if(isAdded){ return }
            showingDialog = true
            super.show(manager, tag)
        }catch (e : Exception) {
            e.printStackTrace()
        }
    }

    private var invokesActionOk:((VehicleAggregate)->Unit)?= null
    fun withActionBtnOk(actionOk : (VehicleAggregate)->Unit) : DialogAddVehicle {
        this.invokesActionOk = actionOk
        return this
    }

    fun showDialogue(fragmentManager: FragmentManager, etiqueta : String){
        show(fragmentManager,etiqueta)
    }

}