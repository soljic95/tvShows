package hr.foreal.showsmarkosoljic.ui.ui.tvShowsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import hr.foreal.showsmarkosoljic.ui.router.Router
import hr.foreal.showsmarkosoljic.ui.router.RouterImpl

class TvShowsPresenter : TvShowsContract.Presenter {


    private lateinit var router: Router
    private lateinit var view: TvShowsContract.View

    override fun setView(view: TvShowsContract.View) {
        this.view = view
        router = RouterImpl((view as Fragment).context!!)
    }

    override fun init() {

    }

    override fun onItemClicked(bundle: Bundle) {
        router.showTvShowDetailsScreen(bundle)
    }

}