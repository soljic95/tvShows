package hr.foreal.showsmarkosoljic.ui.ui.tvShowsList

import android.os.Bundle
import hr.foreal.showsmarkosoljic.ui.base.BasePresenter
import hr.foreal.showsmarkosoljic.ui.router.Router

class TvShowsPresenter(router: Router) : TvShowsContract.Presenter, BasePresenter(router) {

    private lateinit var view: TvShowsContract.View

    override fun setView(view: TvShowsContract.View) {
        this.view = view
    }

    override fun init() {

    }

    override fun listItemClicked(bundle: Bundle) {
        getRouter().showTvShowDetailsScreen(bundle)
    }

}