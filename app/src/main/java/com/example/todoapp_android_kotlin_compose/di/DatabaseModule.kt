package com.example.todoapp_android_kotlin_compose.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp_android_kotlin_compose.data.source.ToDoDao.Companion.DATABASE_NAME
import com.example.todoapp_android_kotlin_compose.data.source.ToDoDatabase
import com.example.todoapp_android_kotlin_compose.ui.ads.AdMobInterstitial
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ToDoDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ToDoDatabase) = database.toDoDao()

    @Singleton
    @Provides
    fun provideAdMobInterstitial(
        @ApplicationContext context: Context
    ): AdMobInterstitial = AdMobInterstitial(context)
}