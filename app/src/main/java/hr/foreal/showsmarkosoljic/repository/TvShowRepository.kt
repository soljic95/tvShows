package hr.foreal.showsmarkosoljic.repository

import androidx.lifecycle.LiveData
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow

interface TvShowRepository {

    fun getAllShows(): LiveData<ArrayList<TvShow>>

    fun addEpisode(tvShowName: String, episode: Episode)
}