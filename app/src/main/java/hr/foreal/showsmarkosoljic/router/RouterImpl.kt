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

class RouterImpl(private val activity: Activity, private val fragmentManager: FragmentManager) : Router {


    private val mainContainer = R.id.activity_main_container


    override fun showMainScreen(intent: Intent) {
        activity.startActivity(intent)
        if (activity is LoginActivity) activity.finish()
    }

    override fun showWelcomeScreen(userName: String) {
        fragmentManager.beginTransaction()
            .replace(mainContainer, WelcomeFragment.newInstance(userName))
            .commit()
    }


    override fun showTvShowsListScreen() {
        fragmentManager.beginTransaction()
            .replace(mainContainer, TvShowsListFragment.newInstance())
            .commit()
    }

    override fun showTvShowDetailsScreen(bundle: Bundle) {
        fragmentManager.beginTransaction()
            .replace(mainContainer, TvShowDetailsFragment.newInstance(bundle), "DETAIL_FRAGMENT")
            .addToBackStack(null)
            .commit()
    }

    override fun goBack() {
        when (fragmentManager.backStackEntryCount) {
            0 -> activity.finish()
            else -> fragmentManager.popBackStack()
        }
    }

    override fun showAddEpisodeScreen(showId: Int) {
        fragmentManager.beginTransaction()
            .replace(
                mainContainer,
                AddEpisodeFragment.newInstance(showId),
                AddEpisodeFragment.ADD_EPISODE_FRAGMENT_TAG
            )
            .addToBackStack(null)
            .commit()
    }

}