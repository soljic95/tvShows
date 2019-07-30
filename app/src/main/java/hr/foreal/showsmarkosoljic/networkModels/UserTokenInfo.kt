package hr.foreal.showsmarkosoljic.networkModels

import com.squareup.moshi.Json

data class UserTokenInfo(
    @Json(name = "id")
    val token: String
)
