package hr.foreal.showsmarkosoljic.ui.router

import android.content.Intent
import android.os.Bundle

interface Router {


    fun showMainScreen(intent: Intent)

    fun showWelcomeScreen(userName: String)

    fun showTvShowsListScreen()

    fun showTvShowDetailsScreen(bundle: Bundle)

    fun goBack()
}