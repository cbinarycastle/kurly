package io.github.cbinarycastle.kurly.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.kurly.data.KurlyService
import io.github.cbinarycastle.kurly.data.product.*
import io.github.cbinarycastle.kurly.data.section.RemoteSectionDataSource
import io.github.cbinarycastle.kurly.data.section.SectionDataSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideSectionDataSource(
        kurlyService: KurlyService,
    ): SectionDataSource = RemoteSectionDataSource(kurlyService)

    @Singleton
    @Provides
    fun provideLocalProductDataSource(
        productDao: ProductDao,
    ): LocalProductDataSource = LocalProductDataSourceImpl(productDao)

    @Singleton
    @Provides
    fun provideRemoteProductDataSource(
        kurlyService: KurlyService,
    ): RemoteProductDataSource = RemoteProductDataSourceImpl(kurlyService)
}