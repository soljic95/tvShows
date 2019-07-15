package hr.foreal.showsmarkosoljic.ui.main

import android.os.Bundle
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.base.BaseActivity
import hr.foreal.showsmarkosoljic.ui.base.BasePresenter
import hr.foreal.showsmarkosoljic.ui.dagger.component.ActivityComponent
import hr.foreal.showsmarkosoljic.ui.interfaces.EpisodeReciver
import hr.foreal.showsmarkosoljic.ui.interfaces.EpisodeSender
import hr.foreal.showsmarkosoljic.ui.model.Episode
import hr.foreal.showsmarkosoljic.ui.ui.login.LoginPresenter
import hr.foreal.showsmarkosoljic.ui.ui.tvShowDetails.TvShowDetailsFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, EpisodeSender {


    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        presenter.setView(this)
        if (savedInstanceState == null) {
            presenter.showWelcomeFragment(intent.getStringExtra(LoginPresenter.INTENT_KEY))
        }
        setSupportActionBar(toolbar)

    }

    private fun init() {
        this.toolbar = findViewById(R.id.toolbar)
    }

    fun getToolbar(): androidx.appcompat.widget.Toolbar {
        return this.toolbar
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }

    override fun sendEpisodeData(episode: Episode) { //ovako sam stavio jer sam radio na brzinu pa eto :( iduci korak je implementirati room i onda samo spremati i povlaciti kako iz koje epizode
        (supportFragmentManager.findFragmentByTag("DETAIL_FRAGMENT") as EpisodeReciver).reciveEpisode(episode)
    }


}
