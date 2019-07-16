package hr.foreal.showsmarkosoljic.ui.addEpisode

interface AddEpisodeContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View)

        fun onUpButtonClicked()

        fun onSaveButtonClicked(title: String, season: String, episode: String, description: String)
    }
}