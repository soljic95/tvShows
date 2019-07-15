package hr.foreal.showsmarkosoljic.ui.dagger.component

import dagger.Component
import hr.foreal.showsmarkosoljic.ui.dagger.exposes.ActivityComponentExposes
import hr.foreal.showsmarkosoljic.ui.dagger.module.ActivityModule
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.ui.ui.login.LoginActivity

@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent : ActivityComponentExposes {

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)
}