package hr.foreal.showsmarkosoljic.ui.tvShowDetails

import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.router.Router

class TvShowDetailsPresenter(router: Router) :
    TvShowDetailsContract.Presenter, BasePresenter(router) {

    private lateinit var view: TvShowDetailsContract.View

    override fun setView(view: TvShowDetailsContract.View) {
        this.view = view
    }

    override fun onUpButtonClicked() {
        getRouter().goBack()
    }

    override fun fabClicked(tvShowName: String) {
        getRouter().showAddEpisodeScreen(tvShowName)
    }
}