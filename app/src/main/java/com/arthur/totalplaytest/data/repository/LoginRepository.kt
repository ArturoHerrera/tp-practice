package com.arthur.totalplaytest.data.repository

import com.arthur.totalplaytest.data.model.AuthRequest
import com.arthur.totalplaytest.data.remote.dto.AuthDto
import com.arthur.totalplaytest.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class LoginRepository(
    private val remoteDS: LoginRemoteDataSource
) {

    //TODO Improve error response
    suspend fun login(body: AuthRequest): Flow<Pair<String, Int>> = flow {
        emit(remoteDS.getRandomPlayingNowMovies(body))
    }.map { result ->
        if (result.succeeded) {
            Pair(result.getDto().session, result.getResponseCode())
        } else {
            Pair(result.getMessage(), result.getErrorCode())
        }
    }
        .filterNotNull()
        .catch { e -> e.printStackTrace() }
        .flowOn(Dispatchers.IO)
}

interface LoginRemoteDataSource {
    suspend fun getRandomPlayingNowMovies(body: AuthRequest): ServiceResult<AuthDto>
}