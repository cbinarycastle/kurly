package io.github.cbinarycastle.kurly.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.kurly.data.DATABASE_NAME
import io.github.cbinarycastle.kurly.data.KurlyDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideKurlyDatabase(
        @ApplicationContext context: Context,
    ): KurlyDatabase = Room.databaseBuilder(
        context,
        KurlyDatabase::class.java,
        DATABASE_NAME
    ).build()
}