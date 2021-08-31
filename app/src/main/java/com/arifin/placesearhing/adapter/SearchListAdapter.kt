package com.arifin.placesearhing.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arifin.placesearhing.R
import com.arifin.placesearhing.`interface`.CellClickListener
import com.arifin.placesearhing.model.nearbyplaces.Result

class SearchListAdapter(
    applicationContext: Context,
    private val dataSet: ArrayList<Result>,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
     var mContext: Context = applicationContext

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { // view initialization
        val name: TextView = view.findViewById(R.id.tvName)
        val address: TextView = view.findViewById(R.id.tvAddress)
        val open: TextView = view.findViewById(R.id.tvOpen)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.search_list_adapter, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // setting value for each item
        viewHolder.name.text = dataSet[position].name
        viewHolder.address.text = dataSet[position].vicinity
        if(dataSet[position].opening_hours?.open_now == true){
            viewHolder.open.text = mContext.getString(R.string.open)
            with(viewHolder) { open.setTextColor(Color.GREEN) }
        }else{
            viewHolder.open.text = mContext.getString(R.string.closed)
            with(viewHolder) { open.setTextColor(Color.RED) }
        }


        viewHolder.itemView.setOnClickListener {  // sending value to activity
            cellClickListener.onCellClickListener(dataSet[position])
        }
    }


    override fun getItemCount() = dataSet.size

}
