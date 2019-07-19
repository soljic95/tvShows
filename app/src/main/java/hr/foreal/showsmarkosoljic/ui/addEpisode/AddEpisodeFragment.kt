package hr.foreal.showsmarkosoljic.ui.addEpisode


import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.base.BaseFragment
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.StaticEpisodes
import hr.foreal.showsmarkosoljic.router.RouterImpl
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import kotlinx.android.synthetic.main.choose_picture_layout.view.*
import kotlinx.android.synthetic.main.fragment_add_episode.*
import kotlinx.android.synthetic.main.number_picker_dialog_layout.view.*

class AddEpisodeFragment() : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance(tvShowName: String): AddEpisodeFragment {
            var fragment = AddEpisodeFragment()
            fragment.setShowName(tvShowName)
            return fragment
        }

        val ADD_EPISODE_FRAGMENT_TAG = "AddEpisode"

    }

    private val MINVALUE = 1
    private val MAXVALUE_S = 20
    private val MAXVALUE_E = 99
    private val NUMBER_TEN = 10
    private val PICTURE_KEY = "PICTURE_KEY"
    private val SEASON_KEY = "SEASON_KEY"
    private val EPISODE_KEY = "EPISODE_KEY"
    private val TVSHOW_KEY = "TVSHOW_KEY"


    private var seasonNumber: Int = 1
    private var episodeNumber: Int = 1
    private var bitmapImage: Bitmap? = null

    private lateinit var showName: String
    private lateinit var presenter: AddEpisodeContract.Presenter


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
            showNumberPickerDialog()
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
        when (showName) {
            "The Office" -> StaticEpisodes.theOffice.listOfEpisodes.add(episode) //todo ENUM?
            "The Big Bang Theory" -> StaticEpisodes.bigBang.listOfEpisodes.add(episode)
            "Jane the Virgin" -> StaticEpisodes.janeTheVirgin.listOfEpisodes.add(episode)
            "House M.D." -> StaticEpisodes.house.listOfEpisodes.add(episode)
            "Sherlock" -> StaticEpisodes.sherlock.listOfEpisodes.add(episode)
            "Its allways sunny in Philadelphia" -> StaticEpisodes.itsAllwaysSunny.listOfEpisodes.add(episode)
        }

        presenter.onUpButtonClicked()

    }

    private fun showPictureDialog() {
        var view = LayoutInflater.from(requireContext()).inflate(R.layout.choose_picture_layout, null, false)

        val pictureDialog = AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setView(view)
            .create()

        pictureDialog.show()

        view.tvCamera.setOnClickListener {
            (activity as MainActivity).checkCameraPermission()
            pictureDialog.dismiss()
        }

        view.tvGallery.setOnClickListener {
            (activity as MainActivity).checkGalleryPermission()
            pictureDialog.dismiss()
        }
    }

    private fun showNumberPickerDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.number_picker_dialog_layout, null, false)

        val numberPickerDialog = AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setView(view)
            .create()

        numberPickerDialog.show()

        view.npSeason.minValue = MINVALUE
        view.npSeason.maxValue = MAXVALUE_S
        view.npEpisode.minValue = MINVALUE
        view.npEpisode.maxValue = MAXVALUE_E

        view.tvBtnSave.setOnClickListener {
            setEpisodeInfo(view.npSeason.value, view.npEpisode.value)
            numberPickerDialog.dismiss()

        }


    }


    fun setEpisodeInfo(season: Int, episode: Int) {
        seasonNumber = season
        episodeNumber = episode

        if (season < NUMBER_TEN) {
            tvSeason.text = String.format(resources.getString(R.string.season_format), "0$seasonNumber")
        } else {
            tvSeason.text = String.format(resources.getString(R.string.season_format), seasonNumber)
        }


        if (episode < NUMBER_TEN) {
            tvEpisode.text = String.format(resources.getString(R.string.episode_format), "0$episodeNumber")
        } else {
            tvEpisode.text = String.format(resources.getString(R.string.season_format), episodeNumber)
        }
    }


    fun setImage(data: Bitmap?) {
        bitmapImage = data
        Glide.with(requireContext())
            .load(data)
            .circleCrop()
            .into(episodeImage)
        icCamera.visibility = View.INVISIBLE
        tvUploadPhoto.visibility = View.INVISIBLE
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (bitmapImage != null) outState.putParcelable(PICTURE_KEY, bitmapImage)
        outState.putInt(SEASON_KEY, seasonNumber)
        outState.putInt(EPISODE_KEY, episodeNumber)
        outState.putString(TVSHOW_KEY, showName)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            if (savedInstanceState.getParcelable<Bitmap>(PICTURE_KEY) != null) {
                bitmapImage = savedInstanceState.getParcelable(PICTURE_KEY)
                setImage(bitmapImage)
            }
            seasonNumber = savedInstanceState.getInt(SEASON_KEY)
            episodeNumber = savedInstanceState.getInt(EPISODE_KEY)
            setEpisodeInfo(seasonNumber, episodeNumber)
            showName = savedInstanceState.getString(TVSHOW_KEY)
        }

    }


}



