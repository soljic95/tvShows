package hr.foreal.showsmarkosoljic.network

import hr.foreal.showsmarkosoljic.networkModels.ApiLoginUserResponse
import hr.foreal.showsmarkosoljic.networkModels.ApiRegisterUserResponse
import hr.foreal.showsmarkosoljic.networkModels.UserLoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IsaApi {

    @POST("users")
    fun registerUser(@Body loginModel: UserLoginModel): Call<ApiRegisterUserResponse>

    @POST("users/sessions")
    fun loginUser(@Body loginModel: UserLoginModel): Call<ApiLoginUserResponse>


}