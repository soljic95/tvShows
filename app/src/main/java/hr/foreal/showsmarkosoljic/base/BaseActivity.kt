package hr.foreal.showsmarkosoljic.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.foreal.showsmarkosoljic.dagger.component.ActivityComponent
import hr.foreal.showsmarkosoljic.dagger.component.DaggerActivityComponent
import hr.foreal.showsmarkosoljic.dagger.module.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = DaggerActivityComponent.builder()
            .applicationComponent((application as BaseApplication).getApplicationComponent())
            .activityModule(ActivityModule(this, supportFragmentManager))
            .build()

        inject(activityComponent)
    }

    abstract fun inject(activityComponent: ActivityComponent)

    fun getActivityComponent(): ActivityComponent {
        return activityComponent
    }

    protected abstract fun getPresenter(): BasePresenter

    override fun onPause() {
        getPresenter().deactivate()
        super.onPause()
    }

    override fun onResume() {
        getPresenter().activate()
        super.onResume()
    }
}