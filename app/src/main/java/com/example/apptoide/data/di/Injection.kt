package com.example.apptoide.data.di

import android.content.Context
import com.example.apptoide.data.remote.ATIReferences.BASE_URL
import com.example.apptoide.data.remote.ServiceATI
import com.example.apptoide.domain.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Injection {

    @Provides
    fun provideGSON(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideAPIService(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideATIService(retrofit: Retrofit): ServiceATI =
        retrofit.create(ServiceATI::class.java)

    @Singleton
    @Provides
    fun provideRepository(remoteDatasource: ServiceATI) = Repository(remoteDatasource)


}
