package hr.foreal.showsmarkosoljic.ui.tvShowDetails

interface TvShowDetailsContract {

    interface View {

    }

    interface Presenter {

        fun setView(view: View)

        fun onUpButtonClicked()

        fun fabClicked(tvShowName: String)
    }
}