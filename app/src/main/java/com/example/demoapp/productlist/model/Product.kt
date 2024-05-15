package com.example.demoapp.productlist.model

data class Product(
    val id: Int = 0,
    val title: String = "",
    val isFavourite: Boolean = false
)

typealias Products = List<Product>