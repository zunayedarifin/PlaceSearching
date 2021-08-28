package com.arifin.placesearhing.model.nearbyplaces

data class NearByPlace(
    val html_attributions: List<Any>,
    val next_page_token: String,
    val results: List<Result>,
    val status: String
)