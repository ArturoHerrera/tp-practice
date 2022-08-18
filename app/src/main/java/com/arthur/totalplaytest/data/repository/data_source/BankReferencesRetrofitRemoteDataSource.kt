package com.arthur.totalplaytest.data.repository.data_source

import com.arthur.totalplaytest.data.model.BankReferencesRequest
import com.arthur.totalplaytest.data.remote.api.BankReferencesApi
import com.arthur.totalplaytest.data.remote.dto.BankReferencesDto
import com.arthur.totalplaytest.data.repository.BankReferencesRemoteDataSource
import com.arthur.totalplaytest.utils.AppPreferences
import com.arthur.totalplaytest.utils.HttpError
import com.arthur.totalplaytest.utils.ServiceResult

class BankReferencesRetrofitRemoteDataSource(
    private val prefs: AppPreferences,
    private val api: BankReferencesApi
) : BankReferencesRemoteDataSource {
    override suspend fun getBankReferences(): ServiceResult<BankReferencesDto> {
        val response = api.getBankReferences(BankReferencesRequest(prefs.getUserToken() ?: "INVALID TOKEN"))

        return if (response.isSuccessful) {
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