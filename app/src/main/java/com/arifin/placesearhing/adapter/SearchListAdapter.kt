package com.arifin.placesearhing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arifin.placesearhing.R
import com.arifin.placesearhing.model.nearbyplaces.Result
import java.util.*
import kotlin.collections.ArrayList

class SearchListAdapter(private val mList: ArrayList<Result>) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    var onItemClick: ((Result) -> Unit)? = null
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_list_adapter, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val Result = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = Result.name

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.tvPatientName)
        init {
            textView.setOnClickListener {
                onItemClick?.invoke(mList[adapterPosition])
            }
        }
    }
}