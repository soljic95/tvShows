package hr.foreal.showsmarkosoljic.networkModels

import com.squareup.moshi.Json

data class ApiLoginUserResponse(
    @Json(name = "token")
    val token: String
)
