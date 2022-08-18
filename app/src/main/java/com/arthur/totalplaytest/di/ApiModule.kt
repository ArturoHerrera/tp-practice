package com.arthur.totalplaytest.di

import com.arthur.totalplaytest.data.remote.api.AuthApi
import com.arthur.totalplaytest.data.remote.api.BankReferencesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    fun provideBankRefService(retrofit: Retrofit): BankReferencesApi = retrofit.create(BankReferencesApi::class.java)

}