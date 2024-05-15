package com.example.demoapp.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.productlist.model.ProductListConfig
import com.example.demoapp.productlist.model.ProductListState
import com.example.demoapp.productlist.usecase.LoadProductsUseCase
import com.example.demoapp.productlist.usecase.ProduceProductListStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val produceProductListState: ProduceProductListStateUseCase,
    loadProducts: LoadProductsUseCase
) : ViewModel() {

    private val config = MutableStateFlow(ProductListConfig())

    val state: StateFlow<ProductListState> =
        loadProducts().combine(config) { products, config ->
            produceProductListState(products, config)
        }.stateIn(viewModelScope, SharingStarted.Lazily, ProductListState())

    infix fun searchBy(keyword: String) {
        config.update { it.copy(searchKeyword = keyword) }
    }

    fun onSearchMenuClicked(showSearchDialog: () -> Unit) {
        if (config.value.searchKeyword.isNotBlank()) {
            searchBy("")
        } else showSearchDialog()
    }

    fun onFavouriteMenuClicked() {
        config.update { it.copy(isFiltered = it.isFiltered.not()) }
    }

    infix fun onProductClicked(productId: Int) {
        config.update {
            it.copy(
                favouriteIds = it.favouriteIds.toMutableSet().apply {
                    if (contains(productId)) {
                        remove(productId)
                    } else add(productId)
                }
            )
        }
    }

}