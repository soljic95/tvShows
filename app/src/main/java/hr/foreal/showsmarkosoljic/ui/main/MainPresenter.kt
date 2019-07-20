package hr.foreal.showsmarkosoljic.ui.main

import hr.foreal.showsmarkosoljic.router.Router

class MainPresenter(private val router: Router) : MainContract.Presenter, BasePresenter() {

    private lateinit var view: MainContract.View


    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun showWelcomeFragment(userName: String) {
        router.showWelcomeScreen(userName)
    }


}