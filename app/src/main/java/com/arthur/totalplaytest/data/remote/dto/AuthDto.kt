package com.arthur.totalplaytest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("session") val session: String = ""
)