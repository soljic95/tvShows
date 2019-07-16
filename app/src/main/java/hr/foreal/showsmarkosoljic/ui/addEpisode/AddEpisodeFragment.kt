package hr.foreal.showsmarkosoljic.ui.addEpisode


import android.os.Bundle
import android.view.*
import android.widget.Toast
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.base.BaseFragment
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.dagger.component.FragmentComponent
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.StaticEpisodes
import kotlinx.android.synthetic.main.fragment_add_episode.*
import kotlinx.android.synthetic.main.fragment_tv_show_details.*
import javax.inject.Inject

class AddEpisodeFragment(private val tvShowName: String) : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance(tvShowName: String): AddEpisodeFragment {
            return AddEpisodeFragment(tvShowName)
        }
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
        btnChoosePicture.setOnClickListener { Toast.makeText(context, "hello marko", Toast.LENGTH_LONG).show() }
        btnSave.setOnClickListener {
            addEpisode(
                etEpisodeName.text.toString(),
                tvSeason.text.toString(),
                tvEpisode.text.toString(),
                etEpisodeDescription.text.toString()
            )
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
        when (tvShowName) {
            "The Office" -> StaticEpisodes.theOffice.listOfEpisodes.add(title)
            "The Big Bang Theory" -> StaticEpisodes.bigBang.listOfEpisodes.add(title)
            "Jane the Virgin" -> StaticEpisodes.janeTheVirgin.listOfEpisodes.add(title)
            "House M.D." -> StaticEpisodes.house.listOfEpisodes.add(title)
            "Sherlock" -> StaticEpisodes.sherlock.listOfEpisodes.add(title)
            "Its allways sunny in Philadelphia" -> StaticEpisodes.itsAllwaysSunny.listOfEpisodes.add(title)
        }

        presenter.onUpButtonClicked()


    }


}
