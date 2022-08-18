package com.arthur.totalplaytest.di

import com.arthur.totalplaytest.data.remote.api.AuthApi
import com.arthur.totalplaytest.data.remote.api.BankReferencesApi
import com.arthur.totalplaytest.data.repository.BankReferencesRepository
import com.arthur.totalplaytest.data.repository.LoginRepository
import com.arthur.totalplaytest.data.repository.data_source.BankReferencesRetrofitRemoteDataSource
import com.arthur.totalplaytest.data.repository.data_source.LoginRetrofitRemoteDataSource
import com.arthur.totalplaytest.utils.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesLoginRepository(
        prefs: AppPreferences,
        authApi: AuthApi
    ): LoginRepository = LoginRepository(
        LoginRetrofitRemoteDataSource(
            prefs = prefs,
            api = authApi
        )
    )

    @ViewModelScoped
    @Provides
    fun providesBankReferencesRepository(
        prefs: AppPreferences,
        bankRefApi: BankReferencesApi
    ): BankReferencesRepository = BankReferencesRepository(
        BankReferencesRetrofitRemoteDataSource(
            prefs = prefs,
            api = bankRefApi
        )
    )

}