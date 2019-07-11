package hr.foreal.showsmarkosoljic.ui.ui.welcome

interface WelcomeContract {

    interface View {

    }

    interface Presenter {
        fun init()

        fun setView(view: View)
    }
}