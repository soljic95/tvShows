package hr.foreal.showsmarkosoljic.ui.ui.tvShowDetails

interface TvShowDetailsContract {

    interface View {

    }

    interface Presenter {

        fun setView(view: View)

        fun onUpButtonClicked()

        fun fabClicked()
    }
}