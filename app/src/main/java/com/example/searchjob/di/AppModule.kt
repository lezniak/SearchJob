package com.example.searchjob.di

import com.example.searchjob.remote.repository.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun getFirebaseRepository() : FirebaseRepository = FirebaseRepository()
}