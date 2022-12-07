package io.github.cbinarycastle.kurly.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.kurly.data.KurlyService
import io.github.cbinarycastle.kurly.data.product.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideProductDataSource(
        kurlyService: KurlyService,
    ): ProductDataSource = RemoteProductDataSource(kurlyService)

    @Singleton
    @Provides
    fun provideLikeDataSource(heartDao: HeartDao): LikeDataSource = LocalLikeDataSource(heartDao)
}