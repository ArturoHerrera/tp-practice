package com.arthur.totalplaytest.data.remote.api

import com.arthur.totalplaytest.data.model.BankReferencesRequest
import com.arthur.totalplaytest.data.remote.dto.BankReferencesDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BankReferencesApi {
    @POST("clienteresp.do")
    suspend fun getBankReferences(
        @Body body: BankReferencesRequest
    ): Response<BankReferencesDto>
}