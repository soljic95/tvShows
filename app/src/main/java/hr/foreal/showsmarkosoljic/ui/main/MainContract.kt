package hr.foreal.showsmarkosoljic.ui.main

class MainContract {

    interface View {

    }

    interface Presenter {
        fun showWelcomeFragment(userName: String)

        fun setView(view: View)
    }
}