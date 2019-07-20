package hr.foreal.showsmarkosoljic.ui.welcome

import android.os.Handler
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.router.Router

class WelcomePresenter(private val router: Router) : WelcomeContract.Presenter, BasePresenter() {
    val ONE_SECOND_DELAY: Long = 1000

    private lateinit var view: WelcomeContract.View

    override fun setView(view: WelcomeContract.View) {
        this.view = view
    }

    override fun init() {
        var handler = Handler()
        handler.postDelayed(this::showListScreen, ONE_SECOND_DELAY)
    }

    private fun showListScreen() {
        router.showTvShowsListScreen()
    }


}