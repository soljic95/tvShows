package hr.foreal.showsmarkosoljic.ui.tvShowDetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.base.BaseFragment
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.dagger.component.FragmentComponent
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow
import hr.foreal.showsmarkosoljic.ui.tvShowsList.TvShowsListFragment
import kotlinx.android.synthetic.main.fragment_tv_show_details.*
import javax.inject.Inject


class TvShowDetailsFragment : BaseFragment(), TvShowDetailsContract.View {


    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): TvShowDetailsFragment {
            var fragment = TvShowDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var episodeList: ArrayList<Episode> = arrayListOf()

    private lateinit var tvShow: TvShow
    private lateinit var adapter: EpisodesRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var presenter: TvShowDetailsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        setHasOptionsMenu(true)
        tvShow = arguments!!.getParcelable(TvShowsListFragment.TV_SHOW_BUNDLE_KEY)
        setToolbar()
        init()
        initRecyclerAdapter()
        setEpisodes()

        fab.setOnClickListener {
            addEpisodes()
        }

        tvAddSomeEpisodes.setOnClickListener { addEpisodes() }


    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }


    private fun setToolbar() {
        tvShowDetailToolbar.setNavigationOnClickListener { presenter.onUpButtonClicked() }
        toolbar_image.setBackgroundResource(tvShow.showDetailsImageId)

    }

    private fun init() {
        tvShowDescription.text = tvShow.showDescription
        tvShowName.text = tvShow.name

    }

    private fun addEpisodes() {
        presenter.fabClicked(tvShow.name)
    }


    private fun initRecyclerAdapter() {
        adapter = EpisodesRecyclerAdapter(LayoutInflater.from(context))
        recyclerView = view!!.findViewById(R.id.episodesRecyclerView)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        if (episodeList.isNotEmpty()) {
            for (ep in episodeList) {
                adapter.addEpisode(ep)
            }
        }
    }

    private fun checkEpisodeList() {
        if (episodeList.isEmpty()) {
            ivSleepingPlaceHolder.visibility = View.VISIBLE
            tvDontWakeHimUp.visibility = View.VISIBLE
            tvSomeoneIsSleeping.visibility = View.VISIBLE
            tvAddSomeEpisodes.visibility = View.VISIBLE
        } else {
            ivSleepingPlaceHolder.visibility = View.GONE
            tvDontWakeHimUp.visibility = View.GONE
            tvSomeoneIsSleeping.visibility = View.GONE
            tvAddSomeEpisodes.visibility = View.GONE

        }
    }

    private fun setEpisodes() {
        adapter.clearAdapter()
        for (episode in tvShow.listOfEpisodes) {
            adapter.addEpisode(episode)
            episodeList.add(episode)
        }
    }

    override fun onResume() {
        checkEpisodeList()
        super.onResume()
    }


}
