package com.ceiba.pruebaadn.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.domain.aggregate.VehicleAggregate
import com.ceiba.domain.util.convertToFormatDate
import com.ceiba.pruebaadn.R
import com.ceiba.pruebaadn.utils.DateFormats
import com.ceiba.pruebaadn.utils.convertToFormatString
import com.ceiba.pruebaadn.utils.hide

class MainRecyclerViewAdapter  (
    val context : Context?,
    private var mValues: MutableList<VehicleAggregate>
) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    private var listener: ((VehicleAggregate)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.aggregate_list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: VehicleAggregate = mValues[position]

        holder.textview_plate.text = item.plate
        if (item.typeId == 1){
            holder.textview_container_moto.hide()
        }else {
            holder.textview_container_car.hide()
            holder.textView_moto.text = context?.getString(R.string.title_motocycle) + "  ${item.cylinder}"
        }
        holder.textView_date.text = item.checkEntity?.dateInput?.convertToFormatDate()?.convertToFormatString(DateFormats.DATE_REDUCED)

        setListeners(holder,item)
    }

    private fun setListeners(holder : ViewHolder, item : VehicleAggregate){

        holder.btn_view_cost.setOnClickListener {
                listener?.invoke(item)
            }
    }

    override fun getItemCount(): Int = mValues.size

    fun setData(listSearch : MutableList<VehicleAggregate>){
        this.mValues = listSearch
        notifyDataSetChanged()
    }

    fun onClickListener(listener : (VehicleAggregate)-> Unit){
        this.listener = listener
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val textview_plate          : TextView = mView.findViewById(R.id.TextViewPlateCard)
        val textview_container_car  : LinearLayout = mView.findViewById(R.id.contentCarCard)
        val textview_container_moto : LinearLayout = mView.findViewById(R.id.contentMotoCard)
        val textView_moto           : TextView = mView.findViewById(R.id.TextViewMotoCard)
        val textView_date           : TextView = mView.findViewById(R.id.TextViewDateTextCard)
        val btn_view_cost           : TextView = mView.findViewById(R.id.btn_view_cost)

    }
}