package com.example.pruebaadn.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.Guideline
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.domain.model.VehicleEntity
import com.example.pruebaadn.R

class DialogCostVehicle : DialogFragment() {

    companion object{
        private var showingDialog = false
        @SuppressLint("StaticFieldLeak")
        private var instance : DialogCostVehicle?= null
        private var routeText : String ?= null

        fun getInstance() : DialogCostVehicle {
            if(instance == null )
            {
                instance =
                    DialogCostVehicle()
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
        mainContainer = inflater.inflate(R.layout.dialog_cost_vehicle,null,false)
        isCancelable = false

        findsViewElements()
        fillView()
        addListeners()
        return mainContainer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findsViewElements()
        fillView()
        addListeners()

    }

    override fun onResume() {
        super.onResume()
        if(routeText != null){
            details_message?.visibility = View.VISIBLE
            details_message?.setText(routeText!!)
        }
    }

    private var image_dialogue_cost : ImageView?= null
    private var details_message : TextView?= null
    private var title : TextView?= null
    private var btn_ok : Button?= null


    private fun findsViewElements(){

        image_dialogue_cost = mainContainer?.findViewById(R.id.image_dialogue_cost)
        details_message     = mainContainer?.findViewById(R.id.details_mess_cost)
        title               = mainContainer?.findViewById(R.id.title_cost_dialog)
        btn_ok              = mainContainer?.findViewById(R.id.btn_accept_cost)

    }


    private fun fillView(){
        selectIcon()
        fillMessage()
    }

    private fun selectIcon(){
        image_dialogue_cost?.setImageResource(typeDialog.getIcono())
    }

    private fun fillMessage(){
        if(routeText == null ){
            details_message?.visibility = View.GONE
            return
        }
        details_message?.visibility = View.VISIBLE
        details_message?.setText(routeText!!)
    }


    private fun addListeners(){

        mainContainer?.setOnClickListener {
            dismiss()
            cleanElementsOfSight()
        }

        btn_ok?.setOnClickListener {
            dismiss()
            cleanElementsOfSight()
        }
    }

    private fun cleanElementsOfSight(){
        invokesActionOk = null
        details_message = null
        routeText = null
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

    enum class TypeDialog{
        WARNING,
        OK
        ;

        fun getIcono(): Int{
            return when(this){
                OK -> R.drawable.ic_check
                WARNING -> R.drawable.ic_warning
            }
        }
    }

    private var invokesActionOk:(()->Unit)?= null
    fun withActionBtnOk(actionOk : ()->Unit) : DialogCostVehicle {
        this.invokesActionOk = actionOk
        return this
    }

    fun withText(routeString : String): DialogCostVehicle {
        routeText = routeString
        return this
    }

    private var typeDialog : TypeDialog = TypeDialog.OK
    fun withTypeDialog(typeDialog : TypeDialog = TypeDialog.OK) : DialogCostVehicle {
        this.typeDialog = typeDialog
        return this
    }

    fun showDialogue(fragmentManager: FragmentManager, etiqueta : String){
        show(fragmentManager,etiqueta)
    }

}