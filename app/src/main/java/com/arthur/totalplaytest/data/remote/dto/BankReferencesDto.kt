package com.arthur.totalplaytest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BankReferencesDto(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("arrayReferences") var arrayReferences: List<BankReference> = listOf()
)

data class BankReference(
    @SerializedName("images") var images: List<Images> = listOf(),
    @SerializedName("bank") var bank: String? = null,
    @SerializedName("reference") var reference: String? = null,
    @SerializedName("aliasbank") var aliasbank: String? = null
)

data class Images(
    @SerializedName("url3X3") var url3X3: String? = null,
    @SerializedName("url4X4") var url4X4: String? = null,
    @SerializedName("url5X5") var url5X5: String? = null,
    @SerializedName("url6X6") var url6X6: String? = null,
    @SerializedName("url7X7") var url7X7: String? = null
)