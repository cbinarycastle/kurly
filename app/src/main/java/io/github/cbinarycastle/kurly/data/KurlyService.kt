package io.github.cbinarycastle.kurly.data

import io.github.cbinarycastle.kurly.data.product.GetProductsResponse
import io.github.cbinarycastle.kurly.data.section.GetSectionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KurlyService {

    @GET("sections")
    suspend fun getSections(@Query("page") page: Int): GetSectionsResponse

    @GET("section/products")
    suspend fun getProducts(@Query("sectionId") sectionId: Int): GetProductsResponse
}