package hr.foreal.showsmarkosoljic.router

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.addEpisode.AddEpisodeFragment
import hr.foreal.showsmarkosoljic.ui.login.LoginActivity
import hr.foreal.showsmarkosoljic.ui.tvShowDetails.TvShowDetailsFragment
import hr.foreal.showsmarkosoljic.ui.tvShowsList.TvShowsListFragment
import hr.foreal.showsmarkosoljic.ui.welcome.WelcomeFragment

class RouterImpl(
    private val activity: Activity,
    private val fragmentManager: FragmentManager,
    private val isTablet: Boolean,
    private val isLandscape: Boolean
) : Router {


    private val masterContainer = R.id.masterLayoutContainer
    private val detailContainer = R.id.detailLayoutContainer
    private val activityContainer = R.id.activity_main_container


    override fun showMainScreen(intent: Intent) {
        activity.startActivity(intent)
        if (activity is LoginActivity) activity.finish()
    }

    override fun showWelcomeScreen(userName: String) {
        fragmentManager.beginTransaction()
            .replace(activityContainer, WelcomeFragment.newInstance(userName), "welcome")
            .commit()
    }


    override fun showTvShowsListScreen() {
        if (isTablet && isLandscape) {
            if (fragmentManager.findFragmentByTag("welcome") != null) {
                fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentByTag("welcome")!!)
                    .replace(masterContainer, TvShowsListFragment.newInstance())
                    .commit()


            }else{
                fragmentManager.beginTransaction()
                    .replace(masterContainer, TvShowsListFragment.newInstance())
                    .commit()
            }
        } else {
            fragmentManager.beginTransaction()
                .replace(activityContainer, TvShowsListFragment.newInstance())
                .commit()
        }

    }

    override fun showTvShowDetailsScreen(bundle: Bundle) {
        if (isTablet && isLandscape) {
            fragmentManager.beginTransaction()
                .replace(detailContainer, TvShowDetailsFragment.newInstance(bundle), "DETAIL_FRAGMENT")
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(activityContainer, TvShowDetailsFragment.newInstance(bundle), "DETAIL_FRAGMENT")
                .addToBackStack(null)
                .commit()
        }
    }

    override fun goBack() {
        when (fragmentManager.backStackEntryCount) {
            0 -> activity.finish()
            else -> fragmentManager.popBackStack()
        }
    }

    override fun showAddEpisodeScreen(showId: Int) {
        if (isTablet && isLandscape) {
            fragmentManager.beginTransaction()
                .replace(
                    detailContainer,
                    AddEpisodeFragment.newInstance(showId),
                    AddEpisodeFragment.ADD_EPISODE_FRAGMENT_TAG
                )
                .addToBackStack(null)
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(
                    activityContainer,
                    AddEpisodeFragment.newInstance(showId),
                    AddEpisodeFragment.ADD_EPISODE_FRAGMENT_TAG
                )
                .addToBackStack(null)
                .commit()
        }

    }

}