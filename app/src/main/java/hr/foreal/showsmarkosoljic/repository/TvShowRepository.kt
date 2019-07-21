package hr.foreal.showsmarkosoljic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow

interface TvShowRepository {

    fun getAllShows(): MutableLiveData<ArrayList<TvShow>>

    fun addEpisode(showId: Int, episode: Episode)

    fun getEpisodes(showId: Int): LiveData<ArrayList<Episode>>


}