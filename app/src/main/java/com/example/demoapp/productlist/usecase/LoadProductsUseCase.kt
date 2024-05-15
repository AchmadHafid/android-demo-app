package com.example.demoapp.productlist.usecase

import com.example.demoapp.productlist.model.Product
import com.example.demoapp.productlist.model.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LoadProductsUseCase @Inject constructor() {

    operator fun invoke(): Flow<Products> = flowOf(dummyProducts)

}

private val dummyProducts = listOf(
    Product(
        id = 1,
        title = "Apple"
    ),
    Product(
        id = 2,
        title = "Samsung"
    ),
    Product(
        id = 3,
        title = "Xiaomi"
    ),
    Product(
        id = 4,
        title = "Vivo"
    ),
    Product(
        id = 5,
        title = "Oppo"
    ),
    Product(
        id = 6,
        title = "Realme"
    ),
    Product(
        id = 7,
        title = "Infinix"
    ),
    Product(
        id = 8,
        title = "Techno"
    ),
    Product(
        id = 9,
        title = "Sony"
    ),
    Product(
        id = 10,
        title = "Asus"
    )
)