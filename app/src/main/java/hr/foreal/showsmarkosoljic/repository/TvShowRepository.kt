package hr.foreal.showsmarkosoljic.repository

import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow

interface TvShowRepository {

    fun getAllShows(): ArrayList<TvShow>

    fun addEpisode(showId: Int, episode: Episode)

    fun getEpisodes(showId: Int): ArrayList<Episode>


}