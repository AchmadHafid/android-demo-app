package com.example.demoapp.productlist.model

data class ProductListConfig(
    val favouriteIds: Set<Int> = emptySet(),
    val isFiltered: Boolean = false,
    val searchKeyword: String = ""
)