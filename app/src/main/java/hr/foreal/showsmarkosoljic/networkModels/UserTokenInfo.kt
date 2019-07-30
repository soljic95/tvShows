package hr.foreal.showsmarkosoljic.networkModels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserTokenInfo(
    @Json(name = "token")
    val userToken: String
)
