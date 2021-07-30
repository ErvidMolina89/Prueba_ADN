package com.example.pruebaadn.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.aggregate.VehicleAggregate
import com.example.pruebaadn.R
import com.example.pruebaadn.utils.hide

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: VehicleAggregate = mValues[position]

        holder.textview_plate.text = item.plate
        if (item.typeId == 1){
            holder.textview_container_moto.hide()
        }else holder.textview_container_car.hide()
        holder.textView_date.text = item.checkEntity?.dateInput

        setListeners(holder,item)
    }

    private fun setListeners(holder : ViewHolder, item : VehicleAggregate){

        holder.btn_view_cost
            .setOnClickListener {
                listener?.invoke(item)
            }
    }

    override fun getItemCount(): Int = mValues.size

    fun setData(listSearch : MutableList<VehicleAggregate>){
        this.mValues = listSearch
        //notifyDataSetChanged()
    }

    fun onClickListener(listener : (VehicleAggregate)-> Unit){
        this.listener = listener
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val textview_plate          : TextView = mView.findViewById(R.id.TextViewPlateCard)
        val textview_container_car  : LinearLayout = mView.findViewById(R.id.contentCarCard)
        val textview_container_moto : LinearLayout = mView.findViewById(R.id.contentMotoCard)
        val textView_date           : TextView = mView.findViewById(R.id.TextViewDateTextCard)
        val btn_view_cost           : TextView = mView.findViewById(R.id.btn_view_cost)

    }
}