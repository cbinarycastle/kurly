package io.github.cbinarycastle.kurly.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.kurly.data.KurlyService
import io.github.cbinarycastle.kurly.data.product.ProductDataSource
import io.github.cbinarycastle.kurly.data.product.RemoteProductDataSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideProductDataSource(
        kurlyService: KurlyService,
    ): ProductDataSource = RemoteProductDataSource(kurlyService)
}