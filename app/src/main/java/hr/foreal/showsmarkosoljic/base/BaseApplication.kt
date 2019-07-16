package hr.foreal.showsmarkosoljic.base

import android.app.Application
import hr.foreal.showsmarkosoljic.dagger.component.ApplicationComponent
import hr.foreal.showsmarkosoljic.dagger.module.ApplicationModule
import hr.foreal.showsmarkosoljic.dagger.component.DaggerApplicationComponent

class BaseApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }


}