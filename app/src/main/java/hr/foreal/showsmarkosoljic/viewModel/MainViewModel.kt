package hr.foreal.showsmarkosoljic.viewModel

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow
import hr.foreal.showsmarkosoljic.repository.TvShowRepository
import hr.foreal.showsmarkosoljic.router.Router

class MainViewModel(private val repository: TvShowRepository) : ViewModel() {
    val ONE_SECOND_DELAY: Long = 1000

    private var showList: MutableLiveData<ArrayList<TvShow>> = MutableLiveData()
    private var episodeList: MutableLiveData<ArrayList<Episode>> = MutableLiveData()
    private lateinit var router: Router

    fun observeShows(): LiveData<ArrayList<TvShow>> {
        showList.value = repository.getAllShows()
        return showList
    }

    fun setRouter(router: Router) {
        this.router = router
    }

    fun listItemClicked(bundle: Bundle) {
        router.showTvShowDetailsScreen(bundle)
    }


    fun observeShowEpisodes(showId: Int): LiveData<ArrayList<Episode>> {
        episodeList.value = repository.getEpisodes(showId)
        return episodeList
    }

    fun showWelcomeFragment(userName: String) = router.showWelcomeScreen(userName)

    fun onUpButtonClicked() {
        router.goBack()
    }

    fun addEpisode(showId: Int, episode: Episode) {
        repository.addEpisode(showId, episode)
    }

    fun fabClicked(showId: Int) {
        router.showAddEpisodeScreen(showId)
    }

    fun init() {
        var handler = Handler()
        handler.postDelayed(this::showListScreen, ONE_SECOND_DELAY)
    }

    private fun showListScreen() {
        router.showTvShowsListScreen()

    }

    fun displayShowList() {
        router.showTvShowsListScreen()
    }

}