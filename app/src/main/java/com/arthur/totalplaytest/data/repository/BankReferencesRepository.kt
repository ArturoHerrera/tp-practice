package com.arthur.totalplaytest.data.repository

import com.arthur.totalplaytest.data.remote.dto.BankReference
import com.arthur.totalplaytest.data.remote.dto.BankReferencesDto
import com.arthur.totalplaytest.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class BankReferencesRepository(
    private val remoteDS: BankReferencesRemoteDataSource,
    private val sessionDS: SessionLocalDataSource
) {

    //TODO Improve error response
    suspend fun getBankReferences(): Flow<Pair<String?, List<BankReference>>> = flow {
        emit(remoteDS.getBankReferences())
    }.map { result ->
        if (result.succeeded) {
            Pair(null, result.getDto().arrayReferences)
        } else {
            Pair(result.getMessage(), listOf())
        }
    }
        .filterNotNull()
        .catch { e -> e.printStackTrace() }
        .flowOn(Dispatchers.IO)


    suspend fun logout(): Boolean = sessionDS.logOut()
}

interface BankReferencesRemoteDataSource {
    suspend fun getBankReferences(): ServiceResult<BankReferencesDto>
}

interface SessionLocalDataSource {
    suspend fun logOut(): Boolean
}