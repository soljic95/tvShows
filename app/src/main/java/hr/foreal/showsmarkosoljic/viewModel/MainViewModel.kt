package hr.foreal.showsmarkosoljic.viewModel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow
import hr.foreal.showsmarkosoljic.repository.TvShowRepository
import hr.foreal.showsmarkosoljic.router.Router

class MainViewModel(private val router: Router, private val repository: TvShowRepository) : ViewModel() {


    fun listItemClicked(bundle: Bundle) {
        router.showTvShowDetailsScreen(bundle)
    }

    fun getAllShows(): LiveData<ArrayList<TvShow>> {
        return repository.getAllShows()
    }

    fun showWelcomeFragment(userName: String) = router.showWelcomeScreen(userName)

    fun onUpButtonClicked() {
        router.goBack()
    }

    fun addEpisode(tvShowName: String, episode: Episode) {
        repository.addEpisode(tvShowName, episode)
    }

    fun fabClicked(tvShowName: String) {
        router.showAddEpisodeScreen(tvShowName)
    }

}