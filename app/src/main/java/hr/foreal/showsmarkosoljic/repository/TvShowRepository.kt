package hr.foreal.showsmarkosoljic.repository

import androidx.lifecycle.LiveData
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow
import hr.foreal.showsmarkosoljic.networkModels.*

interface TvShowRepository {

    fun getAllShows(): ArrayList<TvShow>

    fun addEpisode(showId: Int, episode: Episode)

    fun getEpisodes(showId: Int): ArrayList<Episode>

    fun createAccount(loginModel: UserLoginModel)

    fun observeRegisterUserResponse(): LiveData<RegisterUserResponse>

    fun login(loginModel: UserLoginModel)

    fun observeLoginResponseData(): LiveData<TokenData>


}