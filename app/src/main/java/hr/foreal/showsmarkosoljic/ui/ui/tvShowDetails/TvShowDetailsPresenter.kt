package hr.foreal.showsmarkosoljic.ui.ui.tvShowDetails

import androidx.fragment.app.Fragment
import hr.foreal.showsmarkosoljic.ui.router.Router
import hr.foreal.showsmarkosoljic.ui.router.RouterImpl

class TvShowDetailsPresenter : TvShowDetailsContract.Presenter {

    private lateinit var view: TvShowDetailsContract.View
    private lateinit var router: Router

    override fun setView(view: TvShowDetailsContract.View) {
        this.view = view
        this.router = RouterImpl((view as Fragment).context!!)

    }

    override fun onUpButtonClicked() {
        router.goBack()
    }

}