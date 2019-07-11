package hr.foreal.showsmarkosoljic.ui.ui.welcome

import android.os.Handler
import androidx.fragment.app.Fragment
import hr.foreal.showsmarkosoljic.ui.router.Router
import hr.foreal.showsmarkosoljic.ui.router.RouterImpl

class WelcomePresenter : WelcomeContract.Presenter {
    val ONE_SECOND_DELAY: Long = 1000
    private lateinit var view: WelcomeContract.View
    private lateinit var router: Router


    override fun setView(view: WelcomeContract.View) {

        router = RouterImpl((view as Fragment).context!!)
    }

    override fun init() {
        var handler: Handler = Handler()
        handler.postDelayed(this::showListScreen, ONE_SECOND_DELAY)
    }

    private fun showListScreen() {
        router.showTvShowsListScreen()
    }


}