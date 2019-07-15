package hr.foreal.showsmarkosoljic.ui.router

import android.app.Activity
import android.content.Context
import android.content.Intent

class RouterImpl(private val context: Context) : Router {

    override fun showMainScreen(intent: Intent) {
        context.startActivity(intent)
        (context as Activity).finish()

    }
}