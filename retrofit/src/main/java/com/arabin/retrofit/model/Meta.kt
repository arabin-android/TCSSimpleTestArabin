package com.arabin.retrofit.model

data class Meta(
    val current_page: Int,
    val next_page: Any,
    val per_page: Int,
    val total_count: Int,
    val total_pages: Int
)