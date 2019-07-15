package hr.foreal.showsmarkosoljic.ui.interfaces

import hr.foreal.showsmarkosoljic.ui.model.Episode

interface EpisodeSender {
    fun sendEpisodeData(episode: Episode)
}
