package com.arifin.placesearhing.`interface`

import com.arifin.placesearhing.model.nearbyplaces.Result


interface CellClickListener { // for data sending from adapter to activity
    fun onCellClickListener(data: Result)
}