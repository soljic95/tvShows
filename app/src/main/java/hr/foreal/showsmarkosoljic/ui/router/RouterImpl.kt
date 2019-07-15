package hr.foreal.showsmarkosoljic.ui.router

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.ui.addEpisode.AddEpisodeFragment
import hr.foreal.showsmarkosoljic.ui.ui.login.LoginActivity
import hr.foreal.showsmarkosoljic.ui.ui.tvShowDetails.TvShowDetailsFragment
import hr.foreal.showsmarkosoljic.ui.ui.tvShowsList.TvShowsListFragment
import hr.foreal.showsmarkosoljic.ui.ui.welcome.WelcomeFragment

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

    override fun showAddEpisodeScreen() {
        fragmentManager.beginTransaction()
            .replace(mainContainer, AddEpisodeFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

}