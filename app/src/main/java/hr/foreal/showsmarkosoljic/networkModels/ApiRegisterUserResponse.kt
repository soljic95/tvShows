package hr.foreal.showsmarkosoljic.networkModels

import com.squareup.moshi.Json


 class ApiRegisterUserResponse(
    @Json(name = "type")
    val type: String,
    @Json(name = "type")
    val email: String,
    @Json(name = "_id")
    val token: String
)