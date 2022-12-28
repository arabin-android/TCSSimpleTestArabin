package com.arabin.retrofit.model

/**
 * @author Arabin
 * @since 12/28/2022
 * A data model
 * */
data class TeamDetails(
    val id: Int? = null,
    val abbreviation: String?,
    val city: String?,
    val conference: String?,
    val division: String?,
    val full_name: String?,
    val name: String?
)
