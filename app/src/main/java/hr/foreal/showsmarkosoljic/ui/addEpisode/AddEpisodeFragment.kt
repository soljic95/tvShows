package hr.foreal.showsmarkosoljic.ui.addEpisode


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.base.BaseFragment
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.StaticEpisodes
import hr.foreal.showsmarkosoljic.router.RouterImpl
import hr.foreal.showsmarkosoljic.ui.dialog.ChoosePictureLocation
import hr.foreal.showsmarkosoljic.ui.dialog.NumberPickerDialogFragment
import kotlinx.android.synthetic.main.fragment_add_episode.*
import javax.inject.Inject

class AddEpisodeFragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance(tvShowName: String): AddEpisodeFragment {
            var fragment = AddEpisodeFragment()
            fragment.setShowName(tvShowName)
            return fragment
        }

        val ADD_EPISODE_FRAGMENT_TAG = "AddEpisode"

    }


    private var seasonNumber: Int = 1
    private var episodeNumber: Int = 1
    private lateinit var showName: String
    private lateinit var presenter: AddEpisodeContract.Presenter
    private var pictureUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        setEpisodeInfo(seasonNumber, episodeNumber)
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

    private fun setShowName(tvShowName: String) {
        showName = tvShowName
    }

    override fun setPresenter() {
        presenter = AddEpisodePresenter(RouterImpl(requireActivity(), requireFragmentManager()))

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
        Log.d("marko", showName)
        when (showName) {
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
        seasonNumber = season
        episodeNumber = episode

        if (season < 10) {
            tvSeason.text = String.format(resources.getString(R.string.season_format), "0$seasonNumber")
        } else {
            tvSeason.text = String.format(resources.getString(R.string.season_format), seasonNumber)
        }


        if (episode < 10) {
            tvEpisode.text = String.format(resources.getString(R.string.episode_format), "0$episodeNumber")
        } else {
            tvEpisode.text = String.format(resources.getString(R.string.season_format), episodeNumber)
        }
    }


    fun setImage(data: Uri?) {
        pictureUri = data!!
        Glide.with(context!!)
            .load(data)
            .circleCrop()
            .into(episodeImage)
        icCamera.visibility = View.INVISIBLE
        tvUploadPhoto.visibility = View.INVISIBLE
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (pictureUri != null) outState.putString("PICTURE_URI", pictureUri.toString())
        outState.putInt("SEASON", seasonNumber)
        outState.putInt("EPISODE", episodeNumber)
        outState.putString("TVSHOW", showName)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("PICTURE_URI") != null) {
                pictureUri = savedInstanceState.getString("PICTURE_URI").toUri()
                setImage(pictureUri)
            }
            seasonNumber = savedInstanceState.getInt("SEASON")
            episodeNumber = savedInstanceState.getInt("EPISODE")
            setEpisodeInfo(seasonNumber, episodeNumber)
            showName = savedInstanceState.getString("TVSHOW")
        }

    }


}



