package hr.foreal.showsmarkosoljic.ui.tvShowDetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.model.Episode
import hr.foreal.showsmarkosoljic.model.TvShow
import hr.foreal.showsmarkosoljic.ui.tvShowsList.TvShowsListFragment
import hr.foreal.showsmarkosoljic.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_details.*


class TvShowDetailsFragment : Fragment() {


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

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        tvShow = arguments!!.getParcelable(TvShowsListFragment.TV_SHOW_BUNDLE_KEY)
        setToolbar()
        init()
        initRecyclerAdapter()
        viewModel.observeShowEpisodes(tvShow.id).observe(this, Observer {
            adapter.addEpisodes(it)
            episodeList.addAll(it)

        })
        fab.setOnClickListener {
            addEpisodes()
        }

        tvAddSomeEpisodes.setOnClickListener { addEpisodes() }


    }


    private fun setToolbar() {
        tvShowDetailToolbar.setNavigationOnClickListener { viewModel.onUpButtonClicked() }
        toolbar_image.setBackgroundResource(tvShow.showDetailsImageId)

    }

    private fun init() {
        tvShowDescription.text = tvShow.showDescription
        tvShowName.text = tvShow.name

    }


    private fun addEpisodes() {
        viewModel.fabClicked(tvShow.id)
    }


    private fun initRecyclerAdapter() {
        adapter = EpisodesRecyclerAdapter(LayoutInflater.from(context))
        recyclerView = view!!.findViewById(R.id.episodesRecyclerView)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    private fun checkEpisodeList() {
        if (episodeList.isEmpty()) {
            showViews()
        } else {
            hideViews()

        }
    }

    private fun hideViews() {
        ivSleepingPlaceHolder.visibility = View.GONE
        tvDontWakeHimUp.visibility = View.GONE
        tvSomeoneIsSleeping.visibility = View.GONE
        tvAddSomeEpisodes.visibility = View.GONE
    }

    private fun showViews() {
        ivSleepingPlaceHolder.visibility = View.VISIBLE
        tvDontWakeHimUp.visibility = View.VISIBLE
        tvSomeoneIsSleeping.visibility = View.VISIBLE
        tvAddSomeEpisodes.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        checkEpisodeList()
    }


}
