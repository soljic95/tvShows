package hr.foreal.showsmarkosoljic.ui.ui.tvShowDetails

import hr.foreal.showsmarkosoljic.ui.base.BasePresenter
import hr.foreal.showsmarkosoljic.ui.router.Router

class TvShowDetailsPresenter(router: Router) :
    TvShowDetailsContract.Presenter, BasePresenter(router) {

    private lateinit var view: TvShowDetailsContract.View

    override fun setView(view: TvShowDetailsContract.View) {
        this.view = view
    }

    override fun onUpButtonClicked() {
        getRouter().goBack()
    }

    override fun fabClicked() {
        getRouter().showAddEpisodeScreen()
    }
}