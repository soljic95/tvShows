package hr.foreal.showsmarkosoljic.repository

import androidx.lifecycle.LiveData
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow
import hr.foreal.showsmarkosoljic.networkModels.RegisterUserResponse
import hr.foreal.showsmarkosoljic.networkModels.UserInfoResponse
import hr.foreal.showsmarkosoljic.networkModels.UserLoginModel
import hr.foreal.showsmarkosoljic.networkModels.UserTokenInfo

interface TvShowRepository {

    fun getAllShows(): ArrayList<TvShow>

    fun addEpisode(showId: Int, episode: Episode)

    fun getEpisodes(showId: Int): ArrayList<Episode>

    fun createNewAccount(loginModel: UserLoginModel)

    fun getRegisterUserResponse(): LiveData<RegisterUserResponse>?

    fun loginUser(loginModel: UserLoginModel): LiveData<UserTokenInfo>



}