package hr.foreal.showsmarkosoljic.ui.main

import android.os.Bundle
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.base.BaseActivity
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.dagger.component.ActivityComponent
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.ui.login.LoginPresenter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {


    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setView(this)
        if (savedInstanceState == null) {
            presenter.showWelcomeFragment(intent.getStringExtra(LoginPresenter.INTENT_KEY))
        }

    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }


}
