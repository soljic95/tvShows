package hr.foreal.showsmarkosoljic.dagger.module

import dagger.Module
import dagger.Provides
import hr.foreal.showsmarkosoljic.router.Router
import hr.foreal.showsmarkosoljic.ui.addEpisode.AddEpisodeContract
import hr.foreal.showsmarkosoljic.ui.addEpisode.AddEpisodePresenter
import hr.foreal.showsmarkosoljic.ui.tvShowDetails.TvShowDetailsContract
import hr.foreal.showsmarkosoljic.ui.tvShowDetails.TvShowDetailsPresenter
import hr.foreal.showsmarkosoljic.ui.tvShowsList.TvShowsContract
import hr.foreal.showsmarkosoljic.ui.tvShowsList.TvShowsPresenter
import hr.foreal.showsmarkosoljic.ui.welcome.WelcomeContract
import hr.foreal.showsmarkosoljic.ui.welcome.WelcomePresenter

@Module
class FragmentModule {

    @Provides
    fun provideWelcome(router: Router): WelcomeContract.Presenter {
        return WelcomePresenter(router)
    }

    @Provides
    fun provideTvShowDetailsPresenter(router: Router): TvShowDetailsContract.Presenter {
        return TvShowDetailsPresenter(router)
    }

    @Provides
    fun provideTvShowsPresenter(router: Router): TvShowsContract.Presenter {
        return TvShowsPresenter(router)
    }

    @Provides
    fun provideAddEpisodePresenter(router: Router): AddEpisodeContract.Presenter {
        return AddEpisodePresenter(router)
    }


}