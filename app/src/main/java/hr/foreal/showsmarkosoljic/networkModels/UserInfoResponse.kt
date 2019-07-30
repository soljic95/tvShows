package hr.foreal.showsmarkosoljic.networkModels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserInfoResponse(
    @Json(name = "type")
    val type: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "_id")
    val id: String
)