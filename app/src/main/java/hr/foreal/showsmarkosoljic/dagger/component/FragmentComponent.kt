package hr.foreal.showsmarkosoljic.dagger.component

import dagger.Component
import hr.foreal.showsmarkosoljic.dagger.exposes.FragmentComponentExposes
import hr.foreal.showsmarkosoljic.dagger.module.FragmentModule
import hr.foreal.showsmarkosoljic.ui.addEpisode.AddEpisodeFragment
import hr.foreal.showsmarkosoljic.ui.tvShowDetails.TvShowDetailsFragment
import hr.foreal.showsmarkosoljic.ui.tvShowsList.TvShowsListFragment
import hr.foreal.showsmarkosoljic.ui.welcome.WelcomeFragment

@Component(modules = [FragmentModule::class], dependencies = [ActivityComponent::class])
interface FragmentComponent : FragmentComponentExposes {

    fun inject(tvShowDetailsFragment: TvShowDetailsFragment)

    fun inject(tvShowsListFragment: TvShowsListFragment)

    fun inject(welcomeFragment: WelcomeFragment)

    fun inject(addEpisodeFragment: AddEpisodeFragment)
}