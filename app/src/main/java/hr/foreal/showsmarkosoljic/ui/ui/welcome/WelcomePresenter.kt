package hr.foreal.showsmarkosoljic.ui.ui.welcome

import android.os.Handler
import androidx.fragment.app.Fragment
import hr.foreal.showsmarkosoljic.ui.base.BasePresenter
import hr.foreal.showsmarkosoljic.ui.router.Router
import hr.foreal.showsmarkosoljic.ui.router.RouterImpl

class WelcomePresenter(router: Router) : WelcomeContract.Presenter, BasePresenter(router) {
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
        getRouter().showTvShowsListScreen()
    }


}