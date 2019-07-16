package hr.foreal.showsmarkosoljic.ui.tvShowsList

import android.os.Bundle
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.router.Router

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