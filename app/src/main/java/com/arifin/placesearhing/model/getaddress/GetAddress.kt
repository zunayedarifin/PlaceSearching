package com.arifin.placesearhing.model.getaddress

data class GetAddress(
    val results: List<Result>,
    val status: String
)