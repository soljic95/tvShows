package hr.foreal.showsmarkosoljic.ui.dagger.component

import dagger.Component
import hr.foreal.showsmarkosoljic.ui.dagger.exposes.ApplicationComponentExposes
import hr.foreal.showsmarkosoljic.ui.dagger.module.ApplicationModule

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : ApplicationComponentExposes {
}