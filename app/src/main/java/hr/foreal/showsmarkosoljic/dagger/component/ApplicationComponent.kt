package hr.foreal.showsmarkosoljic.dagger.component

import dagger.Component
import hr.foreal.showsmarkosoljic.dagger.exposes.ApplicationComponentExposes
import hr.foreal.showsmarkosoljic.dagger.module.ApplicationModule

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : ApplicationComponentExposes {
}