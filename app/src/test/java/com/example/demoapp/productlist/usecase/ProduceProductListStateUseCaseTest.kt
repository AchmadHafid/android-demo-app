package com.example.demoapp.productlist.usecase

import com.example.demoapp.R
import com.example.demoapp.productlist.model.ProductListConfig
import org.junit.Assert.assertEquals
import org.junit.Test

class ProduceProductListStateUseCaseTest {

    private val useCase = ProduceProductListStateUseCase()

    @Test
    fun `When search keyword is blank, state should contains standard search icon`() {
        val config = ProductListConfig(searchKeyword = "")
        val state = useCase(products = emptyList(), config = config)

        assertEquals(R.drawable.ic_round_search_24, state.searchIconRes)
    }

    @Test
    fun `When search keyword is not blank, state should contains clear search icon`() {
        val config = ProductListConfig(searchKeyword = "test")
        val state = useCase(products = emptyList(), config = config)

        assertEquals(R.drawable.ic_img_clear_search, state.searchIconRes)
    }

    @Test
    fun `When filter by favourite is inactive, state should contains favourite hollow icon`() {
        val config = ProductListConfig(isFiltered = false)
        val state = useCase(products = emptyList(), config = config)

        assertEquals(R.drawable.ic_round_favorite_border_24, state.favouriteIconRes)
    }

    @Test
    fun `When filter by favourite is active, state should contains favourite bold icon`() {
        val config = ProductListConfig(isFiltered = true)
        val state = useCase(products = emptyList(), config = config)

        assertEquals(R.drawable.ic_round_favorite_24, state.favouriteIconRes)
    }

}