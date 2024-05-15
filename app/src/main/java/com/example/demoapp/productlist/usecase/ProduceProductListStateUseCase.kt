package com.example.demoapp.productlist.usecase

import com.example.demoapp.R
import com.example.demoapp.productlist.model.ProductListConfig
import com.example.demoapp.productlist.model.ProductListState
import com.example.demoapp.productlist.model.Products
import javax.inject.Inject

class ProduceProductListStateUseCase @Inject constructor() {

    operator fun invoke(products: Products, config: ProductListConfig): ProductListState =
        ProductListState(
            products = products.map { it.copy(isFavourite = config.favouriteIds.contains(it.id)) }
                .filter { config.isFiltered.not() || it.isFavourite }
                .filter { config.searchKeyword.isBlank() || it.title.contains(config.searchKeyword, true) },
            searchIconRes = if (config.searchKeyword.isBlank()) {
                R.drawable.ic_round_search_24
            } else R.drawable.ic_img_clear_search,
            favouriteIconRes = if (config.isFiltered) {
                R.drawable.ic_round_favorite_24
            } else R.drawable.ic_round_favorite_border_24
        )

}