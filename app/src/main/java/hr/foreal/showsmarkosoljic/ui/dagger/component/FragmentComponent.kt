package hr.foreal.showsmarkosoljic.ui.dagger.component

import dagger.Component
import hr.foreal.showsmarkosoljic.ui.dagger.exposes.FragmentComponentExposes
import hr.foreal.showsmarkosoljic.ui.dagger.module.FragmentModule
import hr.foreal.showsmarkosoljic.ui.ui.addEpisode.AddEpisodeFragment
import hr.foreal.showsmarkosoljic.ui.ui.tvShowDetails.TvShowDetailsFragment
import hr.foreal.showsmarkosoljic.ui.ui.tvShowsList.TvShowsListFragment
import hr.foreal.showsmarkosoljic.ui.ui.welcome.WelcomeFragment

@Component(modules = [FragmentModule::class], dependencies = [ActivityComponent::class])
interface FragmentComponent : FragmentComponentExposes {

    fun inject(tvShowDetailsFragment: TvShowDetailsFragment)

    fun inject(tvShowsListFragment: TvShowsListFragment)

    fun inject(welcomeFragment: WelcomeFragment)

    fun inject(addEpisodeFragment: AddEpisodeFragment)
}