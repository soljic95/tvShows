package hr.foreal.showsmarkosoljic.ui.main

import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.router.Router

class MainPresenter(router: Router) : MainContract.Presenter, BasePresenter(router) {

    private lateinit var view: MainContract.View


    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun showWelcomeFragment(userName: String) {
        getRouter().showWelcomeScreen(userName)
    }


}