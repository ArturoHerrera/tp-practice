package com.arthur.totalplaytest.data.remote.api

import com.arthur.totalplaytest.data.model.AuthRequest
import com.arthur.totalplaytest.data.remote.dto.AuthDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("cliente.do")
    suspend fun login(
        @Body body: AuthRequest
    ): Response<AuthDto>
}