package hr.foreal.showsmarkosoljic.ui.tvShowsList

import android.os.Bundle
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.router.Router

class TvShowsPresenter(private val router: Router) : TvShowsContract.Presenter, BasePresenter() {

    private lateinit var view: TvShowsContract.View

    override fun setView(view: TvShowsContract.View) {
        this.view = view
    }

    override fun init() {

    }

    override fun listItemClicked(bundle: Bundle) {
        router.showTvShowDetailsScreen(bundle)
    }

}