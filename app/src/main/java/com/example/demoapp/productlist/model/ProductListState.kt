package com.example.demoapp.productlist.model

import androidx.annotation.DrawableRes
import com.example.demoapp.R

data class ProductListState(
    val products: Products = emptyList(),
    @DrawableRes
    val searchIconRes: Int = R.drawable.ic_round_search_24,
    @DrawableRes
    val favouriteIconRes: Int = R.drawable.ic_round_favorite_border_24
)