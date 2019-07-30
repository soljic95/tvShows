package hr.foreal.showsmarkosoljic.network

import hr.foreal.showsmarkosoljic.networkModels.RegisterUserResponse
import hr.foreal.showsmarkosoljic.networkModels.TokenData
import hr.foreal.showsmarkosoljic.networkModels.UserLoginModel
import hr.foreal.showsmarkosoljic.networkModels.UserTokenInfo
import retrofit2.Call
import retrofit2.http.*

interface IsaApi {

    @POST("users")
    fun registerUser(@Body loginModel: UserLoginModel): Call<RegisterUserResponse>

    @POST("users/sessions")
    fun loginUser(@Body loginModel: UserLoginModel): Call<TokenData>


}