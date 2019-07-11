package hr.foreal.showsmarkosoljic.ui.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.ui.ui.login.LoginActivity
import hr.foreal.showsmarkosoljic.ui.ui.tvShowDetails.TvShowDetailsFragment
import hr.foreal.showsmarkosoljic.ui.ui.tvShowsList.TvShowsListFragment
import hr.foreal.showsmarkosoljic.ui.ui.welcome.WelcomeFragment

class RouterImpl(private val context: Context) : Router {


    private val mainContainer = R.id.activity_main_container


    private val fragmentManager: FragmentManager =
        if (context is LoginActivity) context.supportFragmentManager else (context as MainActivity).supportFragmentManager//samo stoji ovako dok ne implementiram dagger


    override fun showMainScreen(intent: Intent) {
        context.startActivity(intent)
        (context as Activity).finish()

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
            .replace(mainContainer, TvShowDetailsFragment.newInstance(bundle))
            .addToBackStack(null)
            .commit()
    }

    override fun goBack() {
        when (fragmentManager.backStackEntryCount) {
            0 -> (context as Activity).finish()
            else -> fragmentManager.popBackStack()
        }
    }
}