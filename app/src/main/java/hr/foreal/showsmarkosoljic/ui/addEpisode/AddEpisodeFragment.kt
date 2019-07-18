package hr.foreal.showsmarkosoljic.ui.addEpisode


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.base.BaseFragment
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.dagger.component.FragmentComponent
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.StaticEpisodes
import hr.foreal.showsmarkosoljic.ui.dialog.ChoosePictureLocation
import hr.foreal.showsmarkosoljic.ui.dialog.NumberPickerDialogFragment
import kotlinx.android.synthetic.main.fragment_add_episode.*
import javax.inject.Inject

class AddEpisodeFragment(private val tvShowName: String) : BaseFragment() {


    companion object {
        @JvmStatic
        fun newInstance(tvShowName: String): AddEpisodeFragment {
            return AddEpisodeFragment(tvShowName)
        }

        val ADD_EPISODE_FRAGMENT_TAG = "AddEpisode"

    }

    @Inject
    lateinit var presenter: AddEpisodeContract.Presenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        episodeImage.setOnClickListener { showPictureDialog() }
        btnSave.setOnClickListener {
            addEpisode(
                etEpisodeName.text.toString(),
                tvSeason.text.toString(),
                tvEpisode.text.toString(),
                etEpisodeDescription.text.toString()
            )
        }


        btnChooseEpisode.setOnClickListener {
            createDialog()
        }


    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun getPresenter(): BasePresenter {

        return presenter as BasePresenter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> backButtonClicked()
        }
        return true
    }

    private fun initToolbar() {
        toolbar2.setNavigationOnClickListener { presenter.onUpButtonClicked() }
    }

    private fun backButtonClicked() {
        presenter.onUpButtonClicked()
    }

    private fun addEpisode(title: String, season: String, episode: String, description: String) {
        val episode = Episode(title, season, episode, description)
        when (tvShowName) {
            "The Office" -> StaticEpisodes.theOffice.listOfEpisodes.add(episode)
            "The Big Bang Theory" -> StaticEpisodes.bigBang.listOfEpisodes.add(episode)
            "Jane the Virgin" -> StaticEpisodes.janeTheVirgin.listOfEpisodes.add(episode)
            "House M.D." -> StaticEpisodes.house.listOfEpisodes.add(episode)
            "Sherlock" -> StaticEpisodes.sherlock.listOfEpisodes.add(episode)
            "Its allways sunny in Philadelphia" -> StaticEpisodes.itsAllwaysSunny.listOfEpisodes.add(episode)
        }

        presenter.onUpButtonClicked()

    }

    private fun showPictureDialog() {
        val dialogFragment = ChoosePictureLocation.newInstance()
        dialogFragment.show(activity?.supportFragmentManager?.beginTransaction(), null)

    }

    private fun createDialog() {

        val dialogFragment = NumberPickerDialogFragment.newInstance()
        dialogFragment.show(activity?.supportFragmentManager?.beginTransaction(), "dialog")
    }

    fun setEpisodeInfo(season: Int, episode: Int) {
        if (season < 10) {
            tvSeason.text = String.format(resources.getString(R.string.season_format), "0$season")
        } else {
            tvSeason.text = String.format(resources.getString(R.string.season_format), season)
        }

        if (episode < 10) {
            tvEpisode.text = String.format(resources.getString(R.string.episode_format), "0$episode")
        } else {
            tvEpisode.text = String.format(resources.getString(R.string.season_format), episode)
        }
    }

    fun setImage(data: Uri?) {
        Glide.with(context!!)
            .load(data)
            .centerCrop()
            .into(episodeImage)
        icCamera.visibility = View.INVISIBLE
        tvUploadPhoto.visibility = View.INVISIBLE
    }

}
