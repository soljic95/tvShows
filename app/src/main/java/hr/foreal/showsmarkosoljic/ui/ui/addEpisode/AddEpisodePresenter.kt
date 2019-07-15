package hr.foreal.showsmarkosoljic.ui.ui.addEpisode

import hr.foreal.showsmarkosoljic.ui.base.BasePresenter
import hr.foreal.showsmarkosoljic.ui.router.Router

class AddEpisodePresenter(router: Router) : BasePresenter(router), AddEpisodeContract.Presenter {


    private lateinit var view: AddEpisodeContract.View

    override fun setView(view: AddEpisodeContract.View) {
        this.view = view
    }

    override fun onUpButtonClicked() {
        getRouter().goBack()
    }

    override fun onSaveButtonClicked(title: String, season: String, episode: String, description: String) {

    }
}