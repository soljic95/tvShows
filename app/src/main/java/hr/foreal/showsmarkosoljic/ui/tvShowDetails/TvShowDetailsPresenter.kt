package hr.foreal.showsmarkosoljic.ui.tvShowDetails

import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.router.Router

class TvShowDetailsPresenter(private val router: Router) :
    TvShowDetailsContract.Presenter, BasePresenter() {

    private lateinit var view: TvShowDetailsContract.View

    override fun setView(view: TvShowDetailsContract.View) {
        this.view = view
    }

    override fun onUpButtonClicked() {
        router.goBack()
    }

    override fun fabClicked(tvShowName: String) {
        router.showAddEpisodeScreen(tvShowName)
    }
}