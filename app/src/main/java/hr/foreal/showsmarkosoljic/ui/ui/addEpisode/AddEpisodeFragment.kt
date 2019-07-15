package hr.foreal.showsmarkosoljic.ui.ui.addEpisode


import android.os.Bundle
import android.view.*
import android.widget.Toast
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.base.BaseFragment
import hr.foreal.showsmarkosoljic.ui.base.BasePresenter
import hr.foreal.showsmarkosoljic.ui.dagger.component.FragmentComponent
import hr.foreal.showsmarkosoljic.ui.interfaces.EpisodeSender
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.ui.model.Episode
import kotlinx.android.synthetic.main.fragment_add_episode.*
import javax.inject.Inject

class AddEpisodeFragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance(): AddEpisodeFragment {
            return AddEpisodeFragment()
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
        setHasOptionsMenu(true)
        var actionBar = (activity as MainActivity).supportActionBar
        actionBar?.title = "Add episode"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun backButtonClicked() {
        presenter.onUpButtonClicked()
    }

    private fun addEpisode(title: String, season: String, episode: String, description: String) {
        var episode = Episode(title, season, episode, description)
        var episodeSender = (activity as EpisodeSender)
        episodeSender.sendEpisodeData(episode)
        presenter.onUpButtonClicked()

    }


}
