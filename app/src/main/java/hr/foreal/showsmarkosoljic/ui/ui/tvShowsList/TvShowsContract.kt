package hr.foreal.showsmarkosoljic.ui.ui.tvShowsList

import android.os.Bundle

class TvShowsContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View)

        fun init()

        fun onItemClicked(bundle: Bundle)
    }
}