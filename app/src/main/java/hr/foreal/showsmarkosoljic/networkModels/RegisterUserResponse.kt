package hr.foreal.showsmarkosoljic.networkModels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterUserResponse(
    @Json(name = "data")
    val data: UserInfoResponse
)
