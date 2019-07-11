package hr.foreal.showsmarkosoljic.ui.main

import hr.foreal.showsmarkosoljic.ui.router.Router
import hr.foreal.showsmarkosoljic.ui.router.RouterImpl

class MainPresenter : MainContract.Presenter {


    private lateinit var view: MainContract.View
    lateinit var router: Router


    override fun showWelcomeFragment(userName: String) {
        router.showWelcomeScreen(userName)
    }

    override fun setView(view: MainContract.View) {
        this.view = view
        router = RouterImpl(view as MainActivity)
    }

}