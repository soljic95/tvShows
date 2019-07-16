package hr.foreal.showsmarkosoljic.dagger.component

import dagger.Component
import hr.foreal.showsmarkosoljic.dagger.exposes.ActivityComponentExposes
import hr.foreal.showsmarkosoljic.dagger.module.ActivityModule
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.ui.login.LoginActivity

@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent : ActivityComponentExposes {

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)
}