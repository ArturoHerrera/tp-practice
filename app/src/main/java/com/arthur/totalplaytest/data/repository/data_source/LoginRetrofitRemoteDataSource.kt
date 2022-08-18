package com.arthur.totalplaytest.data.repository.data_source

import com.arthur.totalplaytest.data.model.AuthRequest
import com.arthur.totalplaytest.data.remote.api.AuthApi
import com.arthur.totalplaytest.data.remote.dto.AuthDto
import com.arthur.totalplaytest.data.repository.LoginRemoteDataSource
import com.arthur.totalplaytest.utils.AppPreferences
import com.arthur.totalplaytest.utils.HttpError
import com.arthur.totalplaytest.utils.ServiceResult

class LoginRetrofitRemoteDataSource(
    private val prefs: AppPreferences,
    private val api: AuthApi
) : LoginRemoteDataSource {

    override suspend fun getRandomPlayingNowMovies(body: AuthRequest): ServiceResult<AuthDto> {
        val response = api.login(body)

        return if (response.isSuccessful) {
            response.body()?.session?.let { safeToken -> prefs.setUserToken(safeToken) }
            ServiceResult.Success(
                response.body(),
                response.code()
            )
        } else {
            ServiceResult.Error(
                HttpError.fromCode(response.code()).errorMsg,
                response.code()
            )
        }
    }

}